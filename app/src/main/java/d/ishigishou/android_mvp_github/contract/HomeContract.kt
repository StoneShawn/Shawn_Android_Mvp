package d.ishigishou.android_mvp_github.contract

interface HomeContract {



    interface IHomePresenter{
        fun toCompare(account: String,password: String)
    }

    interface IHomeView {
        fun showSuccess()
        fun showFail()
    }


}