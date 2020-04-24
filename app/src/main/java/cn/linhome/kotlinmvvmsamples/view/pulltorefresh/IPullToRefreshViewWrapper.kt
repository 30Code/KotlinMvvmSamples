package cn.linhome.kotlinmvpsamples.view.pulltorefresh

/**
 * des:
 * Created by 30Code
 * on 2019/1/1
 */
interface IPullToRefreshViewWrapper<T> {
    /**
     * 设置下拉刷新的View
     *
     * @param pullToRefreshView
     */
    fun setPullToRefreshView(pullToRefreshView: T)

    /**
     * 返回设置的下拉刷新View
     *
     * @return
     */
    fun getPullToRefreshView(): T

    /**
     * 设置回调
     *
     * @param onRefreshCallbackWrapper
     */
    fun setOnRefreshCallbackWrapper(onRefreshCallbackWrapper: OnRefreshCallbackWrapper?)

    /**
     * 设置只能从头部下拉刷新
     */
    fun setModePullFromHeader()

    /**
     * 设置只能上拉加载
     */
    fun setModePullFromFooter()

    /**
     * 设置不可以下拉刷新或者上拉加载更多
     */
    fun setModeDisable()

    /**
     * 设置HeaderView处处于刷新状态
     */
    fun startRefreshingFromHeader()

    /**
     * 设置FooterView处处于刷新状态
     */
    fun startRefreshingFromFooter()

    /**
     * 是否正在刷新中
     *
     * @return
     */
    val isRefreshing: Boolean

    /**
     * 停止刷新
     */
    fun stopRefreshing()

    interface OnRefreshCallbackWrapper {
        /**
         * 下拉触发刷新回调
         */
        fun onRefreshingFromHeader()

        /**
         * 上拉触发刷新回调
         */
        fun onRefreshingFromFooter()
    }
}