package d.ishigishou.android_mvp_github.base.adapter

import android.opengl.Visibility
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mView: SparseArray<View>? = null

    init {
        mView = SparseArray()
    }

    fun <T : View> getView(viewId: Int): T {
        //對已有的view做緩存
        var view: View? = mView?.get(viewId)

        //使用緩存的方式減少findViewById的次數
        if (view == null) {
            view = itemView.findViewById(viewId)
            mView?.put(viewId, view)
        }
        return view as T
    }

    fun <T : View> getViewGroup(viewId: Int): T {

        //對已有的view做緩存
        var view: View? = mView?.get(viewId)
        //使用緩存的方式減少findViewById的次數
        if (view == null) {
            view = itemView.findViewById(viewId)
            mView?.put(viewId, view)
        }

        return view as T
    }


    fun setText(viewId: Int, text: CharSequence) {
        val view = getView<TextView>(viewId)
        view.text = "" + text
    }

    fun setHintText(viewId: Int, text: CharSequence) {
        val view = getView<TextView>(viewId)
        view.hint = "" + text
    }


    /**
     * 設置圖片(本地)
     */
    fun setImageResource(viewId: Int, resId: Int) {
        val iv = getView<ImageView>(viewId)
        iv.setImageResource(resId)
    }

    /**
     * 設置圖片(外部)
     */
    fun setImageUrl(viewId: Int, imageLoader: HolderImageLoader) {
        val iv = getView<ImageView>(viewId)
        imageLoader.loadImage(iv,imageLoader.url)
    }

    abstract class HolderImageLoader(val url: String) {
        /**
         * 複寫加載圖片fun
         * @param iv
         * @param url
         */
        abstract fun loadImage(iv: ImageView, url: String)
    }

    /**
     * 設定View顯示
     */
    fun setViewVisibility(viewId: Int, visibility: Int) {
        getView<View>(viewId).visibility = visibility
    }

    fun setOnItemClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

    fun setOnItemLongClickListener(listener: View.OnLongClickListener) {
        itemView.setOnLongClickListener(listener)
    }

}