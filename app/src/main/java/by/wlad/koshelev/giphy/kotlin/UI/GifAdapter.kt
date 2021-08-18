package by.wlad.koshelev.giphy.kotlin.UI

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import by.wlad.koshelev.giphy.kotlin.Arch.VM
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass
import by.wlad.koshelev.giphy.kotlin.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
         * обычнык клик на итем
         */
        holder.itemView.setOnClickListener {
            val a: GiphyClass = arr[holder.adapterPosition]
            MainScope().launch {
                if (VM.vm.isHaveGif(a.id)) {
                    VM.vm.deleteGif(a)
                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_not_like))
                } else {
                    VM.vm.saveGif(a)
                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_like))
                }
                VM.vm.checkLikeGifs()
            }
        }


        /**
         * долго нажатие для копирования ссылки на гифку
         */
        holder.itemView.setOnLongClickListener {
            // копирование в буфер
            val clipboardManager = app.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("myUrl", arr[holder.adapterPosition].myUrl)
            clipboardManager.setPrimaryClip(clipData)

            // измененный Тост
            val tst = Toast.makeText(app, app.getString(R.string.copy), Toast.LENGTH_SHORT)
            val messageTextView = (tst.getView() as ViewGroup).getChildAt(0) as TextView
            messageTextView.textSize = 20f
            messageTextView.setTextColor(app.resources.getColor(R.color.likeColor))
            tst.show()
            true
        }




        return holder
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val a: GiphyClass = arr[position]

        // сама гифка
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.no_img)
        Glide.with(app).load(a.myUrl).apply(requestOptions).into(holder.img)


        // лайк или нет?
        MainScope().launch {
            if (VM.vm.isHaveGif(a.id)) {
                holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_like))
            }
        }
    }


}