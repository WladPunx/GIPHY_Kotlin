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
         * ПОПУЛЯРНЫЙ слушатель
         */
        VM.vm.tredList.observe(viewLifecycleOwner, Observer {
            tredRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 3)
            tredRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })


        /**
         * ЛЮБИМЫЕ гифки
         */
        VM.vm.likeList.observe(viewLifecycleOwner, Observer {
            likeRecycler_MainFrag.layoutManager = GridLayoutManager(activity, 3)
            likeRecycler_MainFrag.adapter = GifAdapter(activity as AppCompatActivity, it)
        })

    }


}