package d.ishigishou.android_mvp_github.base.adapter

interface MultipleType<in T> {

    fun getLayoutId(item: T,position: Int): Int
}