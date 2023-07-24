package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class MapGeocodeResponse(
    @SerializedName("plus_code")
    val plusCodeResponse: PlusCodeResponse? = null,
    @SerializedName("results")
    val results: MutableList<ResultResponse>? = null,
    @SerializedName("status")
    val status: String? = null
)