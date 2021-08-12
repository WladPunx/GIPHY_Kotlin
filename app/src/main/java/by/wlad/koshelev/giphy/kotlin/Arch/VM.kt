package by.wlad.koshelev.giphy.kotlin.Arch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VM(private val apl: Application) : AndroidViewModel(apl) {
    companion object {
        lateinit var vm: VM
    }

    private val repository: Repository = Repository()

    var tredList: MutableLiveData<MutableList<GiphyClass>> = MutableLiveData(mutableListOf())


    suspend fun getTrending() = withContext(Dispatchers.IO) {
        repository.getTrending()
    }

    suspend fun findGif(id: String): Boolean = withContext(Dispatchers.IO) {
        repository.findGif(id)
    }

    suspend fun addGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        repository.addGif(gif)
    }

    suspend fun deleteGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        repository.deleteGif(gif)
    }
}