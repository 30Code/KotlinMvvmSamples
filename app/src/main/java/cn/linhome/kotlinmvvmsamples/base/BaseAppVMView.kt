package cn.linhome.kotlinmvvmsamples.base

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModelProviders

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/25
 */
abstract class BaseAppVMView<VM : BaseViewModel> : BaseAppView, IView {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    lateinit var mViewModel: VM

    abstract fun attachVMClass() : Class<VM>?

    override fun onBaseInit() {
        initVM()
        super.onBaseInit()
        startObserver()
    }

    open fun startObserver() {}

    private fun initVM() {
        if (attachVMClass() == null) {
            throw RuntimeException("ViewModel must not be null.")
        }
        attachVMClass()?.let {
            mViewModel = ViewModelProviders.of(baseActivity).get(it)
//            baseActivity.lifecycle.addObserver(mViewModel)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
//        baseActivity.lifecycle.removeObserver(mViewModel)
        super.onActivityDestroyed(activity)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDefaultMsg(msg: String) {

    }

    override fun showMsg(msg: String) {

    }

    override fun showError(errorMsg: String) {

    }

}