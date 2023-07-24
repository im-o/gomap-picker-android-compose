package id.barakkadev.coremap.data.model.remote.googlemap.request


import com.google.gson.annotations.SerializedName

data class MapGeocodeRequest(
    @SerializedName("latlng")
    val latlng: String? = null,
    @SerializedName("location_type")
    val locationType: String? = null,
    @SerializedName("result_type")
    val resultType: String? = null
)