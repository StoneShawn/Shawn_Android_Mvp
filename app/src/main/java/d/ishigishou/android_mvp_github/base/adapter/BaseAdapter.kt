package d.ishigishou.android_mvp_github.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(var mContext: Context, var mData: ArrayList<T>, private var mLayoutId: Int) :
    RecyclerView.Adapter<ViewHolder>() {

    protected var mInflater: LayoutInflater?=null
    private var mTypeSupport: MultipleType<T>?=null


    private var mItemClickListener: OnItemClickListener?=null

    private var mItemLongClickListener: OnItemLongClickListener?=null


    init {
        mInflater = LayoutInflater.from(mContext)
    }

    constructor(context: Context,data: ArrayList<T>,typeSupport: MultipleType<T>): this(context,data,-1){
        mTypeSupport = typeSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       if(mTypeSupport !=null){
           //需要多佈局
           mLayoutId = viewType
       }

        //創建view
        val view = mInflater?.inflate(mLayoutId,parent,false)

        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //綁定資料
        bindData(holder,mData[position],position)

        mItemClickListener?.let {
            holder.itemView.setOnClickListener{ mItemClickListener!!.onItemClick(mData[position],position)}
        }

        mItemLongClickListener?.let {
            holder.itemView.setOnLongClickListener{ mItemLongClickListener!!.onItemLongClick(mData[position],position)}
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    /**
     * 將必要參數傳遞出去
     * @param holder
     * @param data
     * @param position
     */
    protected abstract fun bindData(holder: ViewHolder,data: T,position: Int)


    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }

    
}