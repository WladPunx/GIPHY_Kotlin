package by.wlad.koshelev.giphy.kotlin.Giphy.etc


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("downsized")
    var downsized: Downsized,
    @SerializedName("downsized_large")
    var downsizedLarge: DownsizedLarge,
    @SerializedName("downsized_medium")
    var downsizedMedium: DownsizedMedium,
    @SerializedName("downsized_small")
    var downsizedSmall: DownsizedSmall,
    @SerializedName("downsized_still")
    var downsizedStill: DownsizedStill,
    @SerializedName("fixed_height")
    var fixedHeight: FixedHeight,
    @SerializedName("fixed_height_downsampled")
    var fixedHeightDownsampled: FixedHeightDownsampled,
    @SerializedName("fixed_height_small")
    var fixedHeightSmall: FixedHeightSmall,
    @SerializedName("fixed_height_small_still")
    var fixedHeightSmallStill: FixedHeightSmallStill,
    @SerializedName("fixed_height_still")
    var fixedHeightStill: FixedHeightStill,
    @SerializedName("fixed_width")
    var fixedWidth: FixedWidth,
    @SerializedName("fixed_width_downsampled")
    var fixedWidthDownsampled: FixedWidthDownsampled,
    @SerializedName("fixed_width_small")
    var fixedWidthSmall: FixedWidthSmall,
    @SerializedName("fixed_width_small_still")
    var fixedWidthSmallStill: FixedWidthSmallStill,
    @SerializedName("fixed_width_still")
    var fixedWidthStill: FixedWidthStill,
    @SerializedName("hd")
    var hd: Hd,
    @SerializedName("looping")
    var looping: Looping,
    @SerializedName("original")
    var original: Original,
    @SerializedName("original_mp4")
    var originalMp4: OriginalMp4,
    @SerializedName("original_still")
    var originalStill: OriginalStill,
    @SerializedName("preview")
    var preview: Preview,
    @SerializedName("preview_gif")
    var previewGif: PreviewGif,
    @SerializedName("preview_webp")
    var previewWebp: PreviewWebp,
    @SerializedName("480w_still")
    var wStill: WStill
)