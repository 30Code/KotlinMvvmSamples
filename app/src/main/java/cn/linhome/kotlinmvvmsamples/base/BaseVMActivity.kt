package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
abstract class BaseVMActivity/*<VM : BaseViewModel>*/ : BaseActivity(), IView {

//    lateinit var mViewModel: VM
//
//    abstract fun attachVMClass() : Class<VM>?
//
//    open fun startObserver() {}
//
//    override fun init(savedInstanceState: Bundle?) {
//        initVM()
//        super.init(savedInstanceState)
//        startObserver()
//    }
//
//    private fun initVM() {
//        if (attachVMClass() == null) {
//            throw RuntimeException("ViewModel must not be null.")
//        }
//        attachVMClass()?.let {
//            mViewModel = ViewModelProviders.of(this).get(it)
//            lifecycle.addObserver(mViewModel)
//        }
//    }
//
//    override fun onDestroy() {
//        lifecycle.removeObserver(mViewModel)
//        super.onDestroy()
//    }

    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int) : Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId).apply {
        lifecycleOwner = this@BaseVMActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
    }

    abstract fun startObserve()
}