package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
abstract class BaseVMActivity : AppCompatActivity(), IView {

    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int) : Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId).apply {
        lifecycleOwner = this@BaseVMActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}