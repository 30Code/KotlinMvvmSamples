package cn.linhome.kotlinmvvmsamples.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import cn.linhome.kotlinmvpsamples.view.pulltorefresh.PullToRefreshViewWrapper
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.pulltorefresh.FPullToRefreshView
import cn.linhome.library.view.SDAppView

/**
 * des :
 * Created by 30Code
 * date : 2020/4/22
 */
open class BaseAppView : SDAppView
{
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    private var mPullToRefreshViewWrapper: PullToRefreshViewWrapper? = null

    /**
     * 返回下拉刷新包裹对象
     *
     * @return
     */
    fun getPullToRefreshViewWrapper(): PullToRefreshViewWrapper {
        if (mPullToRefreshViewWrapper == null) {
            mPullToRefreshViewWrapper = PullToRefreshViewWrapper()
            val pullToRefreshView = findViewById<View>(R.id.view_pull_to_refresh)
            if (pullToRefreshView is FPullToRefreshView) {
                mPullToRefreshViewWrapper!!.setPullToRefreshView(pullToRefreshView as FPullToRefreshView)
            }
        }
        return mPullToRefreshViewWrapper!!
    }
}