package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("address_components")
    val addressComponents: MutableList<AddressComponentResponse?>? = null,
    @SerializedName("formatted_address")
    val formattedAddress: String? = null,
    @SerializedName("geometry")
    val geometry: GeometryResponse? = null,
    @SerializedName("place_id")
    val placeId: String? = null,
    @SerializedName("types")
    val types: MutableList<String?>? = null
)