package id.barakkadev.coremap.domain.model.mapper

import id.barakkadev.coremap.data.model.remote.googlemap.MapGeocodeResponse
import id.barakkadev.coremap.domain.model.googlemap.MapGeocode

/** Created by github.com/im-o on 7/16/2023. */

object MapGeocodeMapper {
    private const val RESULT_OK = "OK"
    private const val NO_STREET1 = "Jalan Tanpa Nama"
    private const val NO_STREET2 = "Unnamed Road"
    private const val ADMINISTRATIVE_AREA_LEVEL_4 = "administrative_area_level_4"
    private const val MAP_POLITICAL = "political"
    private const val MAP_ROUTE = "route"

    fun fromRemote(response: MapGeocodeResponse): MapGeocode {
        val mapGeocode = MapGeocode()
        if (response.status == RESULT_OK) {
            var formattedAddress: String? = null
            var shortName: String? = null
            var streetName: String? = null

            // Update default location [route]
            for (result in response.results ?: mutableListOf()) {
                if (result.types?.contains(MAP_ROUTE) == true) {
                    formattedAddress = result.formattedAddress
                        ?.replace("$NO_STREET1,", "")
                        ?.replace("$NO_STREET2,", "")
                        ?.trim()
                    for (addressComponent in result.addressComponents ?: mutableListOf()) {
                        if (addressComponent?.types?.contains(MAP_ROUTE) == true) {
                            mapGeocode.streetName = addressComponent.shortName
                            val newShortName = addressComponent.shortName
                                ?.replace(NO_STREET1, "")
                                ?.replace(NO_STREET2, "")
                                ?.trim()
                            shortName = if (newShortName.isNullOrEmpty()) null else newShortName
                            streetName = if (newShortName.isNullOrEmpty()) null else newShortName
                            break
                        }
                    }
                    break
                }
            }

            // Change update location [political|administrative_area_level_4]
            for (result in response.results ?: mutableListOf()) {
                if (result.types?.contains(ADMINISTRATIVE_AREA_LEVEL_4) == true && result.types.contains(MAP_POLITICAL)) {
                    formattedAddress = formattedAddress ?: result.formattedAddress
                    for (addressComponent in result.addressComponents ?: mutableListOf()) {
                        if (addressComponent?.types?.contains(ADMINISTRATIVE_AREA_LEVEL_4) == true && addressComponent.types.contains(MAP_POLITICAL)) {
                            mapGeocode.politicalName = addressComponent.shortName
                            if (streetName != null && addressComponent.shortName != null) shortName = "$streetName, ${addressComponent.shortName}"
                            if (shortName == null) shortName = addressComponent.shortName
                            break
                        }
                    }
                    break
                }
            }

            val defaultAddress = response.plusCodeResponse?.compoundCode ?: response.plusCodeResponse?.globalCode
            mapGeocode.status = response.status
            mapGeocode.compound_code = response.plusCodeResponse?.compoundCode
            mapGeocode.global_code = response.plusCodeResponse?.globalCode
            mapGeocode.formattedAddress = formattedAddress ?: defaultAddress
            mapGeocode.shortName = shortName ?: defaultAddress
        }
        return mapGeocode
    }
}
