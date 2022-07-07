package d.ishigishou.android_mvp_github.model

import d.ishigishou.android_mvp_github.model.bean.UserDetailResult
import d.ishigishou.android_mvp_github.model.bean.UserListResult
import d.ishigishou.android_mvp_github.net.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.Query

class UserDetailModel {

    fun getUser(name: String): Observable<UserDetailResult> {
        return RetrofitManager.service.getUser(name)
    }
}