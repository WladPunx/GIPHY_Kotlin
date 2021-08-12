package by.wlad.koshelev.giphy.kotlin.Giphy


import com.google.gson.annotations.SerializedName

data class GiphyApiTrending(
    @SerializedName("data")
    var `data`: List<GiphyClass>
//    @SerializedName("meta")
//    var meta: Meta,
//    @SerializedName("pagination")
//    var pagination: Pagination
)


data class GiphyApiRandom(
    @SerializedName("data")
    var `data`: GiphyClass
)