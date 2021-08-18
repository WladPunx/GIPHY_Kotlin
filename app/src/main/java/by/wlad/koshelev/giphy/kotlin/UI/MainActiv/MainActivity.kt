package by.wlad.koshelev.giphy.kotlin.UI.MainActiv

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.wlad.koshelev.giphy.kotlin.Arch.VM
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphiDB
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

        // запрос на популярные
        MainScope().launch {
            VM.vm.getTrending()
        }

        // вытаскиваем из БД что есть
        MainScope().launch {
            VM.vm.getAllGif()
        }


    }
}


// метод чтобы прятать клавиатуру
fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}