package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("onclick")
    var onclick: Onclick,
    @SerializedName("onload")
    var onload: Onload,
    @SerializedName("onsent")
    var onsent: Onsent
)