package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class NorthSouthResponse(
    @SerializedName("northeast")
    val northeast: LatLngResponse? = null,
    @SerializedName("southwest")
    val southwest: LatLngResponse? = null
)