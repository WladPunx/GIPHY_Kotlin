package by.wlad.koshelev.giphy.kotlin.Giphy

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val apiKey: String = "xYppPgqo8q3N0aZ6LeaFbn4QhwO9tyL0"

interface ApiGiphy {
    // https://api.giphy.com/v1/gifs/trending?api_key=xYppPgqo8q3N0aZ6LeaFbn4QhwO9tyL0


    @GET("trending")
    suspend fun getTrending(
        @Query("limit") limit: Int = 5,
        @Query("api_key") key: String = apiKey
    ): GiphyApiTrending

    @GET("random")
    suspend fun getRandom(
        @Query("api_key") key: String = apiKey
    ): GiphyApiRandom

    @GET("search")
    suspend fun getSearch(
        @Query("q") text: String,
        @Query("api_key") key: String = apiKey
    ): GiphyApiTrending


    companion object {
        fun create(): ApiGiphy {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.giphy.com/v1/gifs/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiGiphy::class.java)
        }
    }
}