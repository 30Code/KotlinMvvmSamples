package cn.linhome.kotlinmvvmsamples.ui.main.appview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseAppVMView
import cn.linhome.kotlinmvvmsamples.model.bean.WXChapterBean
import cn.linhome.kotlinmvvmsamples.ui.main.vm.WechatViewModel
import cn.linhome.kotlinmvvmsamples.view.pagerindicator.WXChapterTitleTab
import cn.linhome.lib.adapter.FPagerAdapter
import cn.linhome.lib.indicator.adapter.PagerIndicatorAdapter
import cn.linhome.lib.indicator.item.IPagerIndicatorItem
import cn.linhome.lib.utils.FCollectionUtil
import kotlinx.android.synthetic.main.view_wechat.view.*

/**
 *  des : 公众号
 *  Created by 30Code
 *  date : 2020/4/25
 */
class WechatView : BaseAppVMView<WechatViewModel> {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    private val mListChapterBean = mutableListOf<WXChapterBean>()

    override fun onCreateContentView(): Int = R.layout.view_wechat

    override fun attachVMClass(): Class<WechatViewModel>? = WechatViewModel::class.java

    override fun onBaseInit() {
        super.onBaseInit()
        mViewModel.getWXChapters()
    }

    override fun startObserver() {
        super.startObserver()
        mViewModel.apply {
            mWXChapterBean.observe(baseActivity, Observer {
                mListChapterBean.addAll(it.data)
                viewPagerIndicator()
                viewPager()
            })
        }
    }

    private fun viewPager() {
        mPagerAdapter.dataHolder.data = mListChapterBean
        vp_content.setOffscreenPageLimit(2)
        vp_content.setAdapter(mPagerAdapter)
    }

    private fun viewPagerIndicator() {
        view_pager_indicator.setViewPager(vp_content)
        view_pager_indicator.setAdapter(object : PagerIndicatorAdapter() {
            override fun onCreatePagerIndicatorItem(position: Int, viewParent: ViewGroup): IPagerIndicatorItem {
                val item = WXChapterTitleTab(activity)
                val bean: WXChapterBean = FCollectionUtil.get<WXChapterBean>(mListChapterBean, position)
                item.setData(bean)
                return item
            }
        })
    }

    private val mPagerAdapter: FPagerAdapter<WXChapterBean> = object : FPagerAdapter<WXChapterBean>(activity) {
        override fun getView(container: ViewGroup?, position: Int): View {
            var weChatListView = WechatListView(baseActivity)
            weChatListView.setWxChapterBean(mListChapterBean.get(position))
            return weChatListView
        }

    }

}