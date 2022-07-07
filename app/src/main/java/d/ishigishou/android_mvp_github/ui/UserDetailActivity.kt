package d.ishigishou.android_mvp_github.ui


import android.content.Intent
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import d.ishigishou.android_mvp_github.Constants
import d.ishigishou.android_mvp_github.R
import d.ishigishou.android_mvp_github.base.BaseActivity
import d.ishigishou.android_mvp_github.contract.UserDetailContract
import d.ishigishou.android_mvp_github.model.bean.UserDetailResult
import d.ishigishou.android_mvp_github.model.bean.UserListResult
import d.ishigishou.android_mvp_github.presenter.UserDetailPresenter
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.activity_user_detail.view.*

class UserDetailActivity : BaseActivity(),UserDetailContract.IUserDetailView {

    private val mPresenter by lazy { UserDetailPresenter() }


    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_user_detail
    }

    override fun initData() {
        val userData = intent.getSerializableExtra(Constants.BUNDLE_USER_DATA) as UserListResult
        mPresenter.getUser(userData.login)
    }

    override fun initView() {

    }

    override fun start() {
    }

    override fun showUserData(user: UserDetailResult) {
        Glide.with(this)
            .load(user.avatar_url)
            .circleCrop()
            .into(iv_avatar)

        tv_name.text = user.name
        tv_bio.text = user.bio
        tv_login.text = user.login
        tv_location.text = user.location
        tv_link.text = user.html_url
        tv_badge.visibility = if(user.site_admin) View.VISIBLE else View.GONE

        linearlayout_link.visibility = if(user.html_url.isNotEmpty()) View.VISIBLE else View.GONE
        linearlayout_location.visibility = if(user.location.isEmpty()) View.GONE else View.VISIBLE


        view_close.setOnClickListener {
            finish()
        }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }
}