package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("banner_image")
    var bannerImage: String,
    @SerializedName("banner_url")
    var bannerUrl: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("display_name")
    var displayName: String,
    @SerializedName("instagram_url")
    var instagramUrl: String,
    @SerializedName("is_verified")
    var isVerified: Boolean,
    @SerializedName("profile_url")
    var profileUrl: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("website_url")
    var websiteUrl: String
)