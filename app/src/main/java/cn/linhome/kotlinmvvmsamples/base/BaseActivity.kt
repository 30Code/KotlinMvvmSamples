package cn.linhome.kotlinmvvmsamples.base

import android.os.Bundle
import android.view.View
import cn.linhome.kotlinmvpsamples.view.pulltorefresh.PullToRefreshViewWrapper
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.app.App
import cn.linhome.lib.pulltorefresh.FPullToRefreshView
import cn.linhome.lib.title.FTitle
import cn.linhome.lib.title.FTitleItem
import cn.linhome.lib.utils.FViewUtil
import cn.linhome.lib.utils.context.FContext
import cn.linhome.lib.utils.context.FToast
import cn.linhome.library.activity.SDBaseActivity
import qiu.niorgai.StatusBarCompat

/**
 *  des : BaseActivity
 *  Created by 30Code
 *  date : 2020/4/24
 */
abstract class BaseActivity : SDBaseActivity(), FTitle.Callback {
    /**
     * 触摸返回键是否退出App
     */
    protected var mIsExitApp : Boolean = false
    protected var mExitTime : Long = 0

    private lateinit var mPullToRefreshViewWrapper : PullToRefreshViewWrapper

    private lateinit var mTitleView : FTitle

    override fun init(savedInstanceState: Bundle?) {
        showTitle(false)
        initData()
    }

    /**
     * 初始化数据
     */
    open fun initData() {}

    override fun onCreateTitleViewResId(): Int = R.layout.include_title_simple

    override fun setStatusBar() {
        super.setStatusBar()
        StatusBarCompat.setStatusBarColor(this, resources.getColor(R.color.res_main_color))
    }

    override fun onInitTitleView(view: View) {
        super.onInitTitleView(view)
        mTitleView = view.findViewById(R.id.title)
        mTitleView.setCallback(this)
        mTitleView.itemLeft.setImageLeft(R.drawable.ic_arrow_left_white)
    }

    fun getTitleView(): FTitle {
        return mTitleView
    }

    protected open fun showTitle(show: Boolean) {
        if (show) {
            FViewUtil.setVisibility(getTitleView(), View.VISIBLE)
        } else {
            FViewUtil.setVisibility(getTitleView(), View.GONE)
        }
    }

    override fun onClickItemRightTitleBar(index: Int, item: FTitleItem?) {

    }

    override fun onClickItemMiddleTitleBar(index: Int, item: FTitleItem?) {

    }

    override fun onClickItemLeftTitleBar(index: Int, item: FTitleItem?) {
    }

    /**
     * 返回下拉刷新包裹对象
     *
     * @return
     */
    fun getPullToRefreshViewWrapper(): PullToRefreshViewWrapper? {
        mPullToRefreshViewWrapper = PullToRefreshViewWrapper()
        val pullToRefreshView = findViewById<View>(R.id.view_pull_to_refresh)
        if (pullToRefreshView is FPullToRefreshView) {
            mPullToRefreshViewWrapper.setPullToRefreshView(pullToRefreshView as FPullToRefreshView)
        }
        return mPullToRefreshViewWrapper
    }

    fun exitApp(){
        if (System.currentTimeMillis() - mExitTime > 2000) {
            FToast.show(FContext.get().getString(R.string.press_again_exit))
        } else {
            App.exitApp(true)
        }
        mExitTime = System.currentTimeMillis()
    }

    override fun onBackPressed() {
        if (mIsExitApp) {
            exitApp()
        } else {
            super.onBackPressed()
        }
    }
}