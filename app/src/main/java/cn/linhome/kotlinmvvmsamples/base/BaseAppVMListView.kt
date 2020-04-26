package cn.linhome.kotlinmvvmsamples.base

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import cn.linhome.kotlinmvpsamples.view.pulltorefresh.IPullToRefreshViewWrapper
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.utils.context.FResUtil
import cn.linhome.lib.utils.extend.FDrawable
import cn.linhome.library.view.SDRecyclerView
import kotlinx.android.synthetic.main.view_knowledge_list.view.*

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/25
 */
abstract class BaseAppVMListView<VM : BaseViewModel> : BaseAppVMView<VM> {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    /**
     * 每页数据的个数
     */
    protected var mPageSize = 20

    /**
     * 是否是下拉刷新
     */
    protected var mIsRefresh = true

    override fun onBaseInit() {
        super.onBaseInit()
        getPullToRefreshViewWrapper().setOnRefreshCallbackWrapper(object :
            IPullToRefreshViewWrapper.OnRefreshCallbackWrapper {

            override fun onRefreshingFromHeader() {
                mIsRefresh = true
                onRefreshList()
            }

            override fun onRefreshingFromFooter() {
                mIsRefresh = false
                onLoadMoreList()
            }
        })

        rv_knowledge_list.run {
            itemAnimator = DefaultItemAnimator()
            addDividerHorizontal(
                FDrawable().size(FResUtil.dp2px(1f))
                    .color(resources.getColor(R.color.res_bg_activity))
            )
            addOnScrollCallBack (object : SDRecyclerView.OnScrollCallBack {
                override fun onLoadMore() {
                    getPullToRefreshViewWrapper().startRefreshingFromFooter()
                }

            })
        }
    }

    /**
     * 下拉刷新
     */
    abstract fun onRefreshList()

    /**
     * 上拉加载更多
     */
    abstract fun onLoadMoreList()

}