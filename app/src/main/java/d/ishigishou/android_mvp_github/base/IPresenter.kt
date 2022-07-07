package d.ishigishou.android_mvp_github.base

interface IPresenter<in V : IBaseView> {
    fun attachView(mRootView: V)
    fun detachView()
}