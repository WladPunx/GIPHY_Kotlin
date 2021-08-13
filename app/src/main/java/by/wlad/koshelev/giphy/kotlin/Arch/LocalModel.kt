package by.wlad.koshelev.giphy.kotlin.Arch

import by.wlad.koshelev.giphy.kotlin.Giphy.GiphiDB
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalModel {

    suspend fun isHaveGif(id: String): Boolean = withContext(Dispatchers.IO) {
        val a: MutableList<GiphyClass> = GiphiDB.dao.findGif(id)
        a.size != 0
    }

    suspend fun saveGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        GiphiDB.dao.addGif(gif)
    }

    suspend fun deleteGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        GiphiDB.dao.deleteGif(gif)
    }


    suspend fun getAllGif(): MutableList<GiphyClass> = withContext(Dispatchers.IO) {
        return@withContext GiphiDB.dao.getAllGif()
    }
}