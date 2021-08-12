package by.wlad.koshelev.giphy.kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.wlad.koshelev.giphy.kotlin.Giphy.ApiGiphy
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphiDB
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyApiTrending
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // подключение БД
        GiphiDB.app = this

        MainScope().launch {
            val a: GiphyApiTrending = ApiGiphy.create().getTrending()
            Log.e("!!!", "${a}")

            val b: MutableList<GiphyClass> = mutableListOf()
            for (i in a.data) {
                b.add(i)
            }
            Log.e("!!!", "${b}")
            Log.e("!!!", "${b[0]}")
            Log.e("!!!", "${b.size}")


            val c: GiphyClass = b[0]
            c.myUrl = "asdf"
            GiphiDB.dao.deleteAll()
            GiphiDB.dao.addGif(c)
            val d: MutableList<GiphyClass> = GiphiDB.dao.getAll()
            Log.e("!!!", "${d}")

            val ttt: GiphyClass = ApiGiphy.create().getRandom().data
            Log.e("!!!", "${ttt}")


            val sss: GiphyApiTrending = ApiGiphy.create().getSearch("минск")
            val bggg: MutableList<GiphyClass> = mutableListOf()
            for (i in sss.data) {
                bggg.add(i)
            }
            Log.e("!!!", "${bggg[6]}")


        }
    }
}