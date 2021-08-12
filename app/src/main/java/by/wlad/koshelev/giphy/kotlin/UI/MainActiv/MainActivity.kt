package by.wlad.koshelev.giphy.kotlin.UI.MainActiv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.wlad.koshelev.giphy.kotlin.Arch.VM
import by.wlad.koshelev.giphy.kotlin.Giphy.ApiGiphy
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphiDB
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import by.wlad.koshelev.giphy.kotlin.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // поключение ВьюМодель
        VM.vm = ViewModelProvider(this).get(VM::class.java)

        // подключение БД
        GiphiDB.app = this

        MainScope().launch {
            VM.vm.getTrending()
        }


        MainScope().launch {
            val b: MutableList<GiphyClass> = ApiGiphy.create().getTrending().data as MutableList<GiphyClass>
            b[0].inz()
            GiphiDB.dao.deleteGif(b[0])
            GiphiDB.dao.addGif(b[0])
        }
    }
}