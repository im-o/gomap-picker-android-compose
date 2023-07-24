package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class GeometryResponse(
    @SerializedName("bounds")
    val bounds: NorthSouthResponse? = null,
    @SerializedName("location")
    val location: LatLngResponse? = null,
    @SerializedName("location_type")
    val locationType: String? = null,
    @SerializedName("viewport")
    val viewport: NorthSouthResponse? = null
)