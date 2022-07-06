package d.ishigishou.android_mvp_github.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import d.ishigishou.android_mvp_github.R
import d.ishigishou.android_mvp_github.base.BaseActivity
import d.ishigishou.android_mvp_github.contract.HomeContract
import d.ishigishou.android_mvp_github.model.bean.UserListResult
import d.ishigishou.android_mvp_github.presenter.HomePresenter
import d.ishigishou.android_mvp_github.ui.adapter.HomeAdapter

class MainActivity : BaseActivity(),HomeContract.IHomeView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    private var isRefresh: Boolean = true

    /**
     * 懶加載Presenter
     */
    private val mPresenter by lazy { HomePresenter() }
    private var mUserList = ArrayList<UserListResult>()

    private val mAdapter by lazy { HomeAdapter(this,mUserList,R.layout.item_user) }

    init {
        mPresenter.attachView(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        refreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
        refreshLayout.isRefreshing = true
        refreshLayout.setOnRefreshListener(this)
    }

    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter

    }

    override fun start() {
        mPresenter.getUsers()
    }

    override fun showUserListData(userList: ArrayList<UserListResult>) {

        if(isRefresh){
            mAdapter.setData(userList)
            refreshLayout.isRefreshing = false
        }else{
            mAdapter.addData(userList)
            refreshLayout.isRefreshing = false
        }
    }

    override fun showFail() {
        Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun onRefresh() {
        isRefresh = true
        mPresenter.getUsers()
    }


//    fun showSuccess(
}