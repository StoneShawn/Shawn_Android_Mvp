package d.ishigishou.android_mvp_github.presenter

import android.util.Log
import d.ishigishou.android_mvp_github.contract.HomeContract
import d.ishigishou.android_mvp_github.model.HomeModel
import d.ishigishou.android_mvp_github.ui.MainActivity
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Retrofit
import java.util.logging.Logger


class HomePresenter(private val view:HomeContract.IHomeView):HomeContract.IHomePresenter {

    private val homeModel by lazy {
        HomeModel()
    }


    override fun toCompare(account: String, password: String) {
        if(account == "1234" && password =="5678"){
            //ToDo 須通知顯示成功HomeActivity
            view.showSuccess()
        }else{
            //Todo 須通知HomeActivity 顯示失敗
            view.showFail()
        }
    }

    override fun getUsers() {
        val disposable = homeModel.getUserList()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("result",""+it)
            }
    }
}