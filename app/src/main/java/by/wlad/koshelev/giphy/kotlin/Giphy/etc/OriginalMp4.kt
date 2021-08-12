package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class OriginalMp4(
    @SerializedName("height")
    var height: String,
    @SerializedName("mp4")
    var mp4: String,
    @SerializedName("mp4_size")
    var mp4Size: String,
    @SerializedName("width")
    var width: String
)