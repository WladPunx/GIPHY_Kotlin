package by.wlad.koshelev.giphy.kotlin.Arch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import by.wlad.koshelev.giphy.kotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VM(private val apl: Application) : AndroidViewModel(apl) {
    companion object {
        lateinit var vm: VM
    }

    private val repository: Repository = Repository(apl)

    var gifList: MutableLiveData<MutableList<GiphyClass>> = MutableLiveData(mutableListOf()) // полный список ГИФ
    var likeList: MutableLiveData<MutableList<GiphyClass>> = MutableLiveData(mutableListOf()) // список из БД
    var statusForGifList: MutableLiveData<String> = MutableLiveData(apl.getString(R.string.trend)) // статус




    suspend fun getTrending() = withContext(Dispatchers.IO) {
        repository.getTrending()
    }

    suspend fun isHaveGif(id: String): Boolean = withContext(Dispatchers.IO) {
        repository.isHaveGif(id)
    }

    suspend fun saveGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        repository.saveGif(gif)
    }

    suspend fun deleteGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        repository.deleteGif(gif)
    }

    suspend fun getAllGif() = withContext(Dispatchers.IO) {
        repository.getAllGif()
    }

    suspend fun checkLikeGifs() = withContext(Dispatchers.Main) {
        repository.checkLikeGifs()
    }

    suspend fun searchGif(text: String) = withContext(Dispatchers.IO) {
        repository.searchGif(text)
    }

    suspend fun getRandomGif(size: Int) = withContext(Dispatchers.IO) {
        repository.getRandomGif(size)
    }


}