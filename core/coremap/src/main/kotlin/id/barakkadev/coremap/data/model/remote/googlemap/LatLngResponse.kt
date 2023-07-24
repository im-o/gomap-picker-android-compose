package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class LatLngResponse(
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lng")
    val lng: Double? = null
)