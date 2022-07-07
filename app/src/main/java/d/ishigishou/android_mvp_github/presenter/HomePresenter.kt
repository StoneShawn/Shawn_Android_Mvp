package d.ishigishou.android_mvp_github.presenter

import android.util.Log
import d.ishigishou.android_mvp_github.base.BasePresenter
import d.ishigishou.android_mvp_github.contract.HomeContract
import d.ishigishou.android_mvp_github.model.HomeModel
import d.ishigishou.android_mvp_github.ui.MainActivity
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Retrofit
import java.util.logging.Logger


class HomePresenter: BasePresenter<HomeContract.IHomeView>(),HomeContract.IHomePresenter {

    private val homeModel by lazy {
        HomeModel()
    }


    override fun getUsers(since: Int, page: Int) {
        val disposable = homeModel.getUserList(since,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mRootView?.apply {
                    showUserListData(it)
                }
            }

        addSubscription(disposable)
    }
}