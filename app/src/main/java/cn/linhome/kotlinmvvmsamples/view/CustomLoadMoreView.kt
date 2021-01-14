package cn.linhome.kotlinmvvmsamples.view

import cn.linhome.kotlinmvvmsamples.R
import com.chad.library.adapter.base.loadmore.LoadMoreView

class CustomLoadMoreView : LoadMoreView() {
    override fun getLayoutId(): Int {
        return R.layout.view_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}