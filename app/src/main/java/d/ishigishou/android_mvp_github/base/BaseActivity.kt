package d.ishigishou.android_mvp_github.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()

    }


    //載入layout
    abstract fun layoutId(): Int
    //初始化資料
    abstract fun initData()
    //初始化View
    abstract fun initView()
    abstract fun start()
}