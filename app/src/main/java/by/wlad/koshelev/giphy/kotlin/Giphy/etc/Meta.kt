package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("response_id")
    var responseId: String,
    @SerializedName("status")
    var status: Int
)