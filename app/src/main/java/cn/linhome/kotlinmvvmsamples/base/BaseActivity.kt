package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *  des : BaseActivity
 *  Created by 30Code
 *  date : 2020/4/24
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()
}