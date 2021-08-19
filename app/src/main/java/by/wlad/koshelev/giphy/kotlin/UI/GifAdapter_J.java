package by.wlad.koshelev.giphy.kotlin.UI;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import by.wlad.koshelev.giphy.kotlin.Arch.VM;
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass;
import by.wlad.koshelev.giphy.kotlin.R;

public class GifAdapter_J extends RecyclerView.Adapter<GifAdapter_J.MyHolder> {
    AppCompatActivity app;
    List<GiphyClass> arr;

    public GifAdapter_J(AppCompatActivity app, List<GiphyClass> arr) {
        this.app = app;
        this.arr = arr;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ImageView likeImg;

        public MyHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageGif_itemGif);
            likeImg = view.findViewById(R.id.like_img_itemGif);
        }
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infalter = LayoutInflater.from(parent.getContext());
        View itemView = infalter.inflate(R.layout.gif_item, parent, false);
        MyHolder holder = new MyHolder(itemView);

        /**
         //         * долго нажатие для копирования ссылки на гифку
         //         */
//        holder.itemView.setOnLongClickListener {
//            // копирование в буфер
//            val clipboardManager = app.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("myUrl", arr[holder.adapterPosition].myUrl)
//            clipboardManager.setPrimaryClip(clipData)
//
//            // измененный Тост
//            val tst = Toast.makeText(app, app.getString(R.string.copy), Toast.LENGTH_SHORT)
//            val messageTextView = (tst.getView() as ViewGroup).getChildAt(0) as TextView
//            messageTextView.textSize = 20f
//            messageTextView.setTextColor(app.resources.getColor(R.color.likeColor))
//            tst.show()
//            true
//        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) app.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("myUrl", arr.get(holder.getAdapterPosition()).getMyUrl());
                clipboardManager.setPrimaryClip(clipData);

                Toast tst = Toast.makeText(app, app.getString(R.string.copy), Toast.LENGTH_SHORT);
                ViewGroup group = (ViewGroup) tst.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTextColor(app.getResources().getColor(R.color.likeColor));
                tst.show();

                return true;
            }
        });

        return holder;

        /**
         //         * обычнык клик на итем
         //         */
//        holder.itemView.setOnClickListener {
//            val a: GiphyClass = arr[holder.adapterPosition]
//            MainScope().launch {
//                if (VM.vm.isHaveGif(a.id)) {
//                    VM.vm.deleteGif(a)
//                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_not_like))
//                } else {
//                    VM.vm.saveGif(a)
//                    holder.likeImg.setImageDrawable(app.resources.getDrawable(R.drawable.ic_like))
//                }
//                VM.vm.checkLikeGifs()
//            }
//        }
//
//
//        /**
//         * долго нажатие для копирования ссылки на гифку
//         */
//        holder.itemView.setOnLongClickListener {
//            // копирование в буфер
//            val clipboardManager = app.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("myUrl", arr[holder.adapterPosition].myUrl)
//            clipboardManager.setPrimaryClip(clipData)
//
//            // измененный Тост
//            val tst = Toast.makeText(app, app.getString(R.string.copy), Toast.LENGTH_SHORT)
//            val messageTextView = (tst.getView() as ViewGroup).getChildAt(0) as TextView
//            messageTextView.textSize = 20f
//            messageTextView.setTextColor(app.resources.getColor(R.color.likeColor))
//            tst.show()
//            true
//        }


    }


    @Override
    public int getItemCount() {
        return arr.size();
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        GiphyClass a = arr.get(position);

        // сама гифка
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.no_img);
        Glide.with(app).load(a.getMyUrl()).apply(requestOptions).into(holder.img);


        new Thread(new Runnable() {
            public void run() {
                if (VM.vm.isHaveGif(a.getId())) {
                    holder.likeImg.setImageDrawable(app.getResources().getDrawable(R.drawable.ic_like));
                }
            }
        }).start();


    }
}
