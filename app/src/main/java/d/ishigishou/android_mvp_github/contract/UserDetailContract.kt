package d.ishigishou.android_mvp_github.contract

import d.ishigishou.android_mvp_github.base.BasePresenter
import d.ishigishou.android_mvp_github.base.IBaseView
import d.ishigishou.android_mvp_github.base.IPresenter
import d.ishigishou.android_mvp_github.model.bean.UserDetailResult
import d.ishigishou.android_mvp_github.model.bean.UserListResult

interface UserDetailContract {


    interface IUserDetailView: IBaseView{
        fun showUserData(user: UserDetailResult)
    }


    interface IUserDetailPresenter: IPresenter<IUserDetailView>{

        fun getUser(name: String)
    }
}