package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import cn.linhome.library.fragment.SDBaseFragment

/**
 *  des : BaseFragment
 *  Created by 30Code
 *  date : 2021/1/11
 */
abstract class BaseFragment : SDBaseFragment() {

    /**
     * 视图是否加载完毕
     */
    private var mIsViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var mHasLoadData = false

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun attachLayoutRes(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(attachLayoutRes(), null)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mIsViewPrepare = true
        initView(view)
        initData()
        lazyLoadDataIfPrepared()
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && mIsViewPrepare && !mHasLoadData) {
            lazyLoad()
            mHasLoadData = true
        }
    }
}