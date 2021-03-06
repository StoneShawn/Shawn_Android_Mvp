package d.ishigishou.android_mvp_github.model

import d.ishigishou.android_mvp_github.model.bean.UserListResult
import d.ishigishou.android_mvp_github.net.RetrofitManager
import io.reactivex.Observable

class HomeModel {



    fun getUserList(since: Int,page: Int): Observable<ArrayList<UserListResult>>{
        return RetrofitManager.service.getUsers(since,page)
    }
}