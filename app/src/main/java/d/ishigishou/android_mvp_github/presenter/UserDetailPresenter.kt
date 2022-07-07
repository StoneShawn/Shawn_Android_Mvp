package d.ishigishou.android_mvp_github.presenter

import android.util.Log
import d.ishigishou.android_mvp_github.base.BasePresenter
import d.ishigishou.android_mvp_github.contract.UserDetailContract
import d.ishigishou.android_mvp_github.model.UserDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter: BasePresenter<UserDetailContract.IUserDetailView>(),UserDetailContract.IUserDetailPresenter {


    private val userDetailModel by lazy {
        UserDetailModel()
    }

    override fun getUser(name: String) {

        val disposable = userDetailModel.getUser(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                issue->
                mRootView?.apply {
                    showUserData(issue)
                }
            },
                {
                    Log.e("error",""+it)
                }
            )

        addSubscription(disposable)
    }

}