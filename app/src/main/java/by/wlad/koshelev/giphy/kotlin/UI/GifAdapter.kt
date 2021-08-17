package by.wlad.koshelev.giphy.kotlin.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import by.wlad.koshelev.giphy.kotlin.Arch.VM
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import by.wlad.koshelev.giphy.kotlin.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GifAdapter(
    val app: AppCompatActivity,
    val arr: MutableList<GiphyClass>
) : RecyclerView.Adapter<GifAdapter.MyHolder>() {


    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imageGif_itemGif)
        val likeImg: ImageView = view.findViewById(R.id.like_img_itemGif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val infalter = LayoutInflater.from(parent.context)
        val itemView = infalter.inflate(R.layout.gif_item, parent, false)
        val holder = MyHolder(itemView)

        /**
         * клик по лайку
         */
        holder.likeImg.setOnClickListener {
            val a: GiphyClass = arr[holder.adapterPosition]
            MainScope().launch {
                if (VM.vm.isHaveGif(a.id)) {
                    VM.vm.deleteGif(a)
                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_not_like))
                } else {
                    VM.vm.saveGif(a)
                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_like))
                }
//                VM.vm.tredList.value = VM.vm.tredList.value
//                VM.vm.getAllGif()
                VM.vm.checkLikeGifs()
            }

        }

        return holder
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val a: GiphyClass = arr[position]

        // сама гифка

        Glide.with(app).load(a.myUrl).into(holder.img)


        // лайк или нет?
        MainScope().launch {
            if (VM.vm.isHaveGif(a.id)) {
                holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_like))
            }
        }
    }


}