package d.ishigishou.android_mvp_github.contract

import d.ishigishou.android_mvp_github.base.IBaseView
import d.ishigishou.android_mvp_github.base.IPresenter
import d.ishigishou.android_mvp_github.model.bean.UserListResult

interface HomeContract {



    interface IHomePresenter: IPresenter<IHomeView>{
        fun toCompare(account: String,password: String)
        fun getUsers()
    }

    interface IHomeView: IBaseView {
        fun showUserListData(userList: ArrayList<UserListResult>)
        fun showFail()

    }


}