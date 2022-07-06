package d.ishigishou.android_mvp_github.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : IBaseView>: IPresenter<T> {

    var mRootView: T? = null
        private set

    private var compositeDisposable = CompositeDisposable()

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //保證activity結束時取消所有訂閱
        if(!compositeDisposable.isDisposed){
            compositeDisposable.clear()
        }

    }

//    private val isViewAttached : Boolean
//        get() = mRootView !=null
//
//
}