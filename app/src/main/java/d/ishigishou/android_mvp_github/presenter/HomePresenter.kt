package d.ishigishou.android_mvp_github.presenter

import d.ishigishou.android_mvp_github.contract.HomeContract
import d.ishigishou.android_mvp_github.ui.MainActivity



class HomePresenter(private val view:HomeContract.IHomeView):HomeContract.IHomePresenter {


    override fun toCompare(account: String, password: String) {
        if(account == "1234" && password =="5678"){
            //ToDo 須通知顯示成功HomeActivity
            view.showSuccess()
        }else{
            //Todo 須通知HomeActivity 顯示失敗
            view.showFail()
        }
    }
}