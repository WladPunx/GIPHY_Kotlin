package by.wlad.koshelev.giphy.kotlin.Arch

import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    private val localModel: LocalModel = LocalModel()
    private val remoteModel: RemoteModel = RemoteModel()


    /**
     * ПОПУЛЯРНЫЕ гифки
     */
    suspend fun getTrending() = withContext(Dispatchers.IO) {
        val list: MutableList<GiphyClass> = remoteModel.getTrending()

        withContext(Dispatchers.Default) {
            VM.vm.tredList.value?.clear()
            VM.vm.tredList.value?.addAll(list)
        }

        withContext(Dispatchers.Main) {
            VM.vm.tredList.value = VM.vm.tredList.value
        }

    }


    /**
     * есть ли эта ГИФка в БД? возвращает БУЛЕАНТ!!
     */
    suspend fun findGif(id: String): Boolean = withContext(Dispatchers.IO) {
        localModel.findGif(id)
    }

    /**
     * добавить гиф в БД
     */
    suspend fun addGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        localModel.addGif(gif)
    }

    /**
     * удалить гиф из БД
     */
    suspend fun deleteGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        localModel.deleteGif(gif)
    }
}