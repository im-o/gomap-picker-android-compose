package id.barakkadev.coremap.domain.model.googlemap

import com.google.android.gms.maps.model.LatLng

/** Created by github.com/im-o on 7/16/2023. */

data class MapGeocode(
    var status: String? = null,
    var compound_code: String? = null,
    var global_code: String? = null,
    var latLng: LatLng? = null,
    var formattedAddress: String? = null,
    var shortName: String? = null,
    var streetName: String? = null,
    var politicalName: String? = null,
)