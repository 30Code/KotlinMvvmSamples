package cn.linhome.kotlinmvvmsamples.ui.main.appview

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.WechatListAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseAppVMListView
import cn.linhome.kotlinmvvmsamples.model.bean.WXChapterBean
import cn.linhome.kotlinmvvmsamples.ui.main.vm.WechatListViewModel
import kotlinx.android.synthetic.main.view_knowledge_list.view.*

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/25
 */
class WechatListView : BaseAppVMListView<WechatListViewModel> {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    private var mCid : Int = 0

    private var mWechatListAdapter : WechatListAdapter? = null

    override fun onCreateContentView(): Int = R.layout.view_knowledge_list

    override fun attachVMClass(): Class<WechatListViewModel>? = WechatListViewModel::class.java

    fun setWxChapterBean(bean: WXChapterBean) {
        mCid = bean.id
        mViewModel.getArticlesList(0, mCid)
    }

    override fun onBaseInit() {
        super.onBaseInit()

        mWechatListAdapter = WechatListAdapter(activity)
        rv_knowledge_list.adapter = mWechatListAdapter
    }

    override fun startObserver() {
        super.startObserver()
        mViewModel.apply {
            mArticleResponseBody.observe(baseActivity, Observer {
                it.data.let {
                    mWechatListAdapter.run {
                        if (mIsRefresh) {
                            mWechatListAdapter?.dataHolder?.data = it.datas
                        } else {
                            mWechatListAdapter?.dataHolder?.appendData(it.datas)
                        }
                    }
                }
                getPullToRefreshViewWrapper().stopRefreshing()
            })
        }
    }

    override fun onRefreshList() {
        mViewModel.getArticlesList(0, mCid)
    }

    override fun onLoadMoreList() {
        val page = mWechatListAdapter!!.itemCount / mPageSize
        mViewModel.getArticlesList(page, mCid)
    }

}