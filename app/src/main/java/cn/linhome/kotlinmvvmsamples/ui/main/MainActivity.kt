package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
    }

    override fun initData() {
    }
}
