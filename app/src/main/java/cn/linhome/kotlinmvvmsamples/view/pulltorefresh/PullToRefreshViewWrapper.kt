package cn.linhome.kotlinmvpsamples.view.pulltorefresh

import cn.linhome.kotlinmvpsamples.view.pulltorefresh.IPullToRefreshViewWrapper.OnRefreshCallbackWrapper
import cn.linhome.lib.pulltorefresh.BuildConfig
import cn.linhome.lib.pulltorefresh.FPullToRefreshView
import cn.linhome.lib.pulltorefresh.PullToRefreshView
import cn.linhome.lib.pulltorefresh.PullToRefreshView.OnRefreshCallback

/**
 * 对下拉刷新进行包裹，避免更换框架的时候要修改大量代码
 */
class PullToRefreshViewWrapper : IPullToRefreshViewWrapper<FPullToRefreshView?> {

    private var mPullToRefreshView: FPullToRefreshView? = null
    private var mOnRefreshCallbackWrapper: OnRefreshCallbackWrapper? = null

    override fun setPullToRefreshView(pullToRefreshView: FPullToRefreshView?) {
        if (mPullToRefreshView !== pullToRefreshView) {
            mPullToRefreshView = pullToRefreshView
            if (pullToRefreshView != null) {
                pullToRefreshView.setOnRefreshCallback(mInternalOnRefreshCallback)
                pullToRefreshView.setDebug(BuildConfig.DEBUG)
            }
        }
    }

    private val mInternalOnRefreshCallback: OnRefreshCallback = object : OnRefreshCallback {
        override fun onRefreshingFromHeader(view: PullToRefreshView) {
            if (mOnRefreshCallbackWrapper != null) {
                mOnRefreshCallbackWrapper!!.onRefreshingFromHeader()
            }
        }

        override fun onRefreshingFromFooter(view: PullToRefreshView) {
            if (mOnRefreshCallbackWrapper != null) {
                mOnRefreshCallbackWrapper!!.onRefreshingFromFooter()
            }
        }
    }

    override fun getPullToRefreshView(): FPullToRefreshView {
        return mPullToRefreshView!!
    }

    override fun setOnRefreshCallbackWrapper(onRefreshCallbackWrapper: OnRefreshCallbackWrapper?) {
        mOnRefreshCallbackWrapper = onRefreshCallbackWrapper
    }

    override fun setModePullFromHeader() {
        getPullToRefreshView().mode = PullToRefreshView.Mode.PULL_FROM_HEADER
    }

    override fun setModePullFromFooter() {
        getPullToRefreshView().mode = PullToRefreshView.Mode.PULL_FROM_FOOTER
    }

    override fun setModeDisable() {
        getPullToRefreshView().mode = PullToRefreshView.Mode.PULL_DISABLE
    }

    override fun startRefreshingFromHeader() {
        getPullToRefreshView().startRefreshingFromHeader()
    }

    override fun startRefreshingFromFooter() {
        getPullToRefreshView().startRefreshingFromFooter()
    }

    override val isRefreshing: Boolean
        get() = getPullToRefreshView().isRefreshing

    override fun stopRefreshing() {
        getPullToRefreshView().stopRefreshing()
    }
}