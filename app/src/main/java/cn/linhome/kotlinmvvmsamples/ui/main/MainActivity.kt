package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreateContentView(): Int = R.layout.act_main

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        mIsExitApp = true
    }
}
