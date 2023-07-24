package id.barakkadev.coremap.data.model.remote.googlemap


import com.google.gson.annotations.SerializedName

data class PlusCodeResponse(
    @SerializedName("compound_code")
    val compoundCode: String? = null,
    @SerializedName("global_code")
    val globalCode: String? = null
)