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
import by.wlad.koshelev.giphy.kotlin.R
import by.wlad.koshelev.giphy.kotlin.UI.GifAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainFrag : Fragment() {


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
                VM.vm.getRandomGif()
                VM.vm.statusForGifList.value = "${getString(R.string.random)}"
            }
        }


        /**
         * статус списка гифок
         */
        VM.vm.statusForGifList.observe(viewLifecycleOwner, Observer {
            statusGifList_txt_MAinFrag.setText(it)
        })


        /**
         * слушатель на ИНТЕРНЕТ гифки
         */
        VM.vm.viewGifList.observe(viewLifecycleOwner, Observer {
            tredRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 2)
            tredRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })


        /**
         * ЛЮБИМЫЕ гифки (из БД)
         */
        VM.vm.likeList.observe(viewLifecycleOwner, Observer {
            likeRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 3)
            likeRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })


        /**
         * управление сеткой
         */

        next_btn_MainFrag.setOnClickListener {
            VM.vm.numberList.value = VM.vm.numberList.value?.plus(1)
            VM.vm.setViewGifList()
        }

        back_btn_MainFrag.setOnClickListener {
            VM.vm.numberList.value = VM.vm.numberList.value?.minus(1)
            VM.vm.setViewGifList()
        }

        VM.vm.numberList.observe(viewLifecycleOwner, Observer {
            numberList_txt_MainFrag.setText("${it}")

            if (it == VM.vm.maxNumberList) next_btn_MainFrag.isEnabled = false
            else next_btn_MainFrag.isEnabled = true

            if (it == 1) back_btn_MainFrag.isEnabled = false
            else back_btn_MainFrag.isEnabled = true
        })

    }


}