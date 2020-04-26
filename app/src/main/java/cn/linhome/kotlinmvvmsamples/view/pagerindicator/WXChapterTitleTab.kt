package cn.linhome.kotlinmvvmsamples.view.pagerindicator

import android.content.Context
import android.util.AttributeSet
import cn.linhome.kotlinmvvmsamples.model.bean.WXChapterBean
import cn.linhome.kotlinmvvmsamples.view.ChapterTabUnderline
import cn.linhome.lib.indicator.item.IPagerIndicatorItem
import cn.linhome.lib.indicator.model.PositionData
import cn.linhome.lib.utils.context.FResUtil

/**
 * 首页分类标题Item
 */
class WXChapterTitleTab : ChapterTabUnderline, IPagerIndicatorItem {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private var mData: WXChapterBean? = null

    private fun init() {
        minimumWidth = FResUtil.dp2px(65f)
        val padding = FResUtil.dp2px(10f)
        getTextViewTitle().setPadding(padding, 0, padding, 0)
        getTextViewTitle().textSize = 14f
    }

    fun setData(data : WXChapterBean) {
        mData = data
        if (mData != null) {
            getTextViewTitle().text = mData!!.name
        }
    }

    fun getData() : WXChapterBean? {
        return mData
    }

    override fun onSelectChanged(selected: Boolean) {
        setSelected(selected)
    }

    override fun onShowPercent(showPercent: Float, isEnter: Boolean, isMoveLeft: Boolean) {

    }

    override fun getPositionData(): PositionData? {
        return null
    }
}