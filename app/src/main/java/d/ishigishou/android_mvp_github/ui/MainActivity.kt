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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), HomeContract.IHomeView, SwipeRefreshLayout.OnRefreshListener {


    /**
     * 懶加載Presenter
     */
    private val mPresenter by lazy { HomePresenter() }
    private val mAdapter by lazy { HomeAdapter(this, mUserList, R.layout.item_user) }


    private var mUserList = ArrayList<UserListResult>()
    private var isRefresh: Boolean = true
    private var currentPage: Int = 20

    /**
     * RecyclerView LoadMore
     */
    private var itemCount: Int = 0
    private var lastPosition: Int = 0
    private var lastItemCount: Int = 0
    lateinit var lastPositionData: UserListResult


    init {
        mPresenter.attachView(this)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

        refreshLayout.isRefreshing = true
        refreshLayout.setOnRefreshListener(this)
    }

    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearManager = recyclerView.layoutManager as LinearLayoutManager

                itemCount = linearManager.itemCount
                lastPosition = linearManager.findLastCompletelyVisibleItemPosition()

                if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
                    lastItemCount = itemCount

                    if (lastItemCount < 100) {
                        isRefresh = false
                        getLoadMoreData()
                    }
                }
            }
        })

    }

    override fun start() {
        mPresenter.getUsers(0, currentPage)
    }

    override fun showUserListData(userList: ArrayList<UserListResult>) {
        mUserList.addAll(userList)
        lastPositionData = userList[userList.size-1]
        if (isRefresh) {
            mAdapter.setData(userList)
            refreshLayout.isRefreshing = false
        } else {
            mAdapter.addData(userList)
            refreshLayout.isRefreshing = false
        }
    }

    override fun showFail() {
        Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun onRefresh() {
        isRefresh = true
        mPresenter.getUsers(0, currentPage)
    }

    fun getLoadMoreData(){
        mPresenter.getUsers(lastPositionData.id, currentPage)
    }

}