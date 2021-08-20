package by.wlad.koshelev.giphy.kotlin.UI.MainFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import by.wlad.koshelev.giphy.kotlin.Arch.VM
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import by.wlad.koshelev.giphy.kotlin.R
import by.wlad.koshelev.giphy.kotlin.UI.GifAdapter
import by.wlad.koshelev.giphy.kotlin.UI.ListConvertorForView
import by.wlad.koshelev.giphy.kotlin.UI.MainActiv.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainFrag : Fragment() {

    private val listConvertor: ListConvertorForView<GiphyClass> = ListConvertorForView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /**
         * кнопка ПОПУЛЯРНЫЕ
         */
        trend_popular_MainFrag.setOnClickListener {
            MainScope().launch {
                VM.vm.getTrending()
                VM.vm.statusForGifList.value = getString(R.string.trend)
            }
        }


        /**
         * кнопка ПОИСК
         */
        findGif_btn_MainFrag.setOnClickListener {
            val text: String = search_txt_MainFrag.text.toString()
            if (text != null && text != "") {

                search_txt_MainFrag.hideKeyboard() // прячем клаву

                MainScope().launch {
                    VM.vm.searchGif(text)
                    VM.vm.statusForGifList.value = "${getString(R.string.search)} : ${text}"
                }
            }
        }

        /**
         * случайная ГИФка
         */
        random_btn_MainFrag.setOnClickListener {
            MainScope().launch {
                VM.vm.getRandomGif(4)
                VM.vm.statusForGifList.value = "${getString(R.string.random)}"
            }
        }


        /**
         * ЛЮБИМЫЕ гифки (из БД)
         */
        VM.vm.likeList.observe(viewLifecycleOwner, Observer {
            likeGifRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 3)
            likeGifRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })

        /**
         * статус списка гифок
         */
        VM.vm.statusForGifList.observe(viewLifecycleOwner, Observer {
            statusGifList_txt_MAinFrag.setText(it)
        })


        /**
         * слушатель на ИНТЕРНЕТ гифки
         */
        // когда придет весь списко из АПИшки
        // мы его загоним в наш конвертов
        VM.vm.gifList.observe(viewLifecycleOwner, Observer {
            listConvertor.new(it, 10)
        })

        // а когда данные в нашем конверторе меняются - мы выводим их на экран
        listConvertor.output.observe(viewLifecycleOwner, Observer {
            inetGifRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 2)
            inetGifRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })


        /**
         * управление сеткой
         */

        next_btn_MainFrag.setOnClickListener {
            listConvertor.setNumber(listConvertor.number.value!! + 1)
        }

        back_btn_MainFrag.setOnClickListener {
            listConvertor.setNumber(listConvertor.number.value!! - 1)
        }

        listConvertor.number.observe(viewLifecycleOwner, Observer {
            numberList_txt_MainFrag.setText("${it}")

            if (it >= listConvertor.maxNumber) next_btn_MainFrag.isEnabled = false
            else next_btn_MainFrag.isEnabled = true

            if (it <= 1) back_btn_MainFrag.isEnabled = false
            else back_btn_MainFrag.isEnabled = true


        })

    }


}