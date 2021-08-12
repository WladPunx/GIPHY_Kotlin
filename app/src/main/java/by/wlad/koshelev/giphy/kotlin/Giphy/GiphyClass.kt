package by.wlad.koshelev.giphy.kotlin.Giphy


import androidx.appcompat.app.AppCompatActivity
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gyphi_table")
data class GiphyClass(
    @SerializedName("id")
    @PrimaryKey var id: String,
    var myUrl: String = ""
//    @SerializedName("analytics")
//    var analytics: Analytics,
//    @SerializedName("analytics_response_payload")
//    var analyticsResponsePayload: String,
//    @SerializedName("bitly_gif_url")
//    var bitlyGifUrl: String,
//    @SerializedName("bitly_url")
//    var bitlyUrl: String,
//    @SerializedName("content_url")
//    var contentUrl: String,
//    @SerializedName("embed_url")
//    var embedUrl: String,
//    @SerializedName("images")
//    var images: Images,
//    @SerializedName("import_datetime")
//    var importDatetime: String,
//    @SerializedName("is_sticker")
//    var isSticker: Int,
//    @SerializedName("rating")
//    var rating: String,
//    @SerializedName("slug")
//    var slug: String,
//    @SerializedName("source")
//    var source: String,
//    @SerializedName("source_post_url")
//    var sourcePostUrl: String,
//    @SerializedName("source_tld")
//    var sourceTld: String,
//    @SerializedName("title")
//    var title: String,
//    @SerializedName("trending_datetime")
//    var trendingDatetime: String,
//    @SerializedName("type")
//    var type: String,
//    @SerializedName("url")
//    var url: String,
//    @SerializedName("user")
//    var user: User,
//    @SerializedName("username")
//    var username: String
) {
    fun inz() {
        this.myUrl = "https://i.giphy.com/media/${this.id}/giphy.gif"
    }
}

@Dao
interface GiphiDAO {

    @Insert
    suspend fun addGif(gif: GiphyClass)

    @Delete
    suspend fun deleteGif(gif: GiphyClass)

    @Query("select * from gyphi_table where id like :id ")
    suspend fun findGif(id: String): MutableList<GiphyClass>

}


@Database(entities = arrayOf(GiphyClass::class), version = 1)
abstract class GiphiDB : RoomDatabase() {
    abstract fun getGyphiDAO(): GiphiDAO

    companion object {
        lateinit var app: AppCompatActivity
        val bd: GiphiDB by lazy {
            Room.databaseBuilder(
                app,
                GiphiDB::class.java,
                "gyphi_db"
            )
                .build()
        }
        val dao: GiphiDAO by lazy {
            bd.getGyphiDAO()
        }
    }
}