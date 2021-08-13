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
            VM.vm.gifList.value?.clear()
            VM.vm.gifList.value?.addAll(list)
        }

        withContext(Dispatchers.Main) {
            VM.vm.gifList.value = VM.vm.gifList.value
        }
    }


    suspend fun searchGif(text: String) = withContext(Dispatchers.IO) {
        val list: MutableList<GiphyClass> = remoteModel.searchGif(text)

        withContext(Dispatchers.Default) {
            VM.vm.gifList.value?.clear()
            VM.vm.gifList.value?.addAll(list)
        }

        withContext(Dispatchers.Main) {
            VM.vm.gifList.value = VM.vm.gifList.value
        }
    }


    /**
     * есть ли эта ГИФка в БД? возвращает БУЛЕАНТ!!
     */
    suspend fun isHaveGif(id: String): Boolean = withContext(Dispatchers.IO) {
        localModel.isHaveGif(id)
    }

    /**
     * добавить гиф в БД
     */
    suspend fun saveGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        localModel.saveGif(gif)
    }

    /**
     * удалить гиф из БД
     */
    suspend fun deleteGif(gif: GiphyClass) = withContext(Dispatchers.IO) {
        localModel.deleteGif(gif)
    }


    suspend fun getAllGif() = withContext(Dispatchers.IO) {
        val likeList: MutableList<GiphyClass> = localModel.getAllGif()

        withContext(Dispatchers.Default) {
            VM.vm.likeList.value?.clear()
            VM.vm.likeList.value?.addAll(likeList)
        }

        withContext(Dispatchers.Main) {
            VM.vm.likeList.value = VM.vm.likeList.value
        }
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
    suspend fun checkLikeGifs() = withContext(Dispatchers.IO) {
        getAllGif()
        withContext(Dispatchers.Main) {
            VM.vm.gifList.value = VM.vm.gifList.value
        }
    }


}