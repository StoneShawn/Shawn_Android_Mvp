package d.ishigishou.android_mvp_github.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import d.ishigishou.android_mvp_github.R
import d.ishigishou.android_mvp_github.base.adapter.BaseAdapter
import d.ishigishou.android_mvp_github.base.adapter.ViewHolder
import d.ishigishou.android_mvp_github.model.bean.UserListResult

class HomeAdapter(mContext: Activity, list: ArrayList<UserListResult>, layoutId: Int) :
    BaseAdapter<UserListResult>(mContext, list, layoutId) {


    fun setData(userList: ArrayList<UserListResult>){
        mData.clear()
        mData = userList
        notifyDataSetChanged()
    }

    fun addData(userList: ArrayList<UserListResult>){
        mData.addAll(userList)
        notifyDataSetChanged()

    }
    override fun bindData(holder: ViewHolder, data: UserListResult, position: Int) {

        holder.setText(R.id.tv_name, "${data.login}")


    }

}