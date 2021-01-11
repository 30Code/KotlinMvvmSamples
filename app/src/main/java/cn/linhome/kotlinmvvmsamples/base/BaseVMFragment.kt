package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *  des : BaseVMFragment
 *  Created by 30Code
 *  date : 2021/1/11
 */
abstract class BaseVMFragment/*<VM : BaseViewModel>*/ : BaseFragment() {

//    lateinit var mViewModel: VM
//
//    abstract fun attachVMClass(): Class<VM>?
//
//    open fun startObserver() {}
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        initVM()
//        startObserver()
//        super.onViewCreated(view, savedInstanceState)
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

    protected  fun < T : ViewDataBinding> binding(inflater: LayoutInflater, @LayoutRes layoutId: Int, container: ViewGroup?) : T =
        DataBindingUtil.inflate<T>(inflater,layoutId, container,false).apply {
        lifecycleOwner = this@BaseVMFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startObserve()
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()
    abstract fun startObserve()
}