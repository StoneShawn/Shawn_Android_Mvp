package d.ishigishou.android_mvp_github.api

import d.ishigishou.android_mvp_github.model.bean.UserDetailResult
import d.ishigishou.android_mvp_github.model.bean.UserListResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {



    /**
     * Github 取得使用者列表
     */
    @GET("/users")
    fun getUsers(@Query("since") since: Int,@Query("per_page") per_page: Int): Observable<ArrayList<UserListResult>>


    /**
     * Github 取得使用者詳細資訊
     */
    @GET("/users/{username}")
    fun getUser(@Path("username") name: String): Observable<UserDetailResult>
}