package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class AddressComponentResponse(
    @SerializedName("long_name")
    val longName: String? = null,
    @SerializedName("short_name")
    val shortName: String? = null,
    @SerializedName("types")
    val types: MutableList<String?>? = null
)