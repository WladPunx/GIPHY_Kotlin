package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class FixedHeightDownsampled(
    @SerializedName("height")
    var height: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("webp")
    var webp: String,
    @SerializedName("webp_size")
    var webpSize: String,
    @SerializedName("width")
    var width: String
)