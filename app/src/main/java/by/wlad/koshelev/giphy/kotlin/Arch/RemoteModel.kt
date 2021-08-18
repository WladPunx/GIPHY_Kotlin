package by.wlad.koshelev.giphy.kotlin.Arch

import by.wlad.koshelev.giphy.kotlin.Giphy.ApiGiphy
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteModel {

    /**
     * популярные гифки
     */
    suspend fun getTrending(): MutableList<GiphyClass> = withContext(Dispatchers.IO) {
        try {
            val req: MutableList<GiphyClass> = ApiGiphy.create().getTrending().data as MutableList<GiphyClass>
            req.map { it.inz() }
            return@withContext req
        } catch (ex: Exception) {
            return@withContext mutableListOf()
        }
    }

    /**
     * поиск гиф
     */
    suspend fun searchGif(text: String): MutableList<GiphyClass> = withContext(Dispatchers.IO) {
        try {
            val req: MutableList<GiphyClass> = ApiGiphy.create().searchGif(text).data as MutableList<GiphyClass>
            req.map { it.inz() }
            return@withContext req
        } catch (ex: Exception) {
            return@withContext mutableListOf()
        }
    }

    /**
     * случайная гиф
     */
    suspend fun getRandomGif(): GiphyClass? = withContext(Dispatchers.IO) {
        try {
            val req: GiphyClass = ApiGiphy.create().getRandom().data
            req.inz()
            return@withContext req
        } catch (ex: Exception) {
            return@withContext null
        }
    }


}