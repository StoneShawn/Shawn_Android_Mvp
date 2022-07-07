package d.ishigishou.android_mvp_github.ui.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import d.ishigishou.android_mvp_github.Constants
import d.ishigishou.android_mvp_github.R
import d.ishigishou.android_mvp_github.base.adapter.BaseAdapter
import d.ishigishou.android_mvp_github.base.adapter.ViewHolder
import d.ishigishou.android_mvp_github.model.bean.UserListResult
import d.ishigishou.android_mvp_github.ui.UserDetailActivity

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

        holder.setViewVisibility(R.id.tv_badge, if(data.site_admin) View.VISIBLE else View.GONE)
        holder.setImageUrl(R.id.iv_avatar, object: ViewHolder.HolderImageLoader(data.avatar_url){
            override fun loadImage(iv: ImageView, url: String) {
                Glide.with(mContext)
                    .load(url)
                    .circleCrop()
                    .into(iv)
            }

        })

        holder.setOnItemClickListener(View.OnClickListener {
            val intent = Intent(mContext as Activity, UserDetailActivity::class.java)
            intent.putExtra(Constants.BUNDLE_USER_DATA, data)
            mContext.startActivity(intent)
        })



    }





}