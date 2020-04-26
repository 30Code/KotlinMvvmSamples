package cn.linhome.kotlinmvvmsamples.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.select.config.FTextViewSelectConfig
import cn.linhome.lib.select.config.FViewSelectConfig
import cn.linhome.lib.select.view.FSelectView
import kotlinx.android.synthetic.main.item_tab_underline.view.*

/**
 * 带标题文字和下划线的Tab
 */
open class ChapterTabUnderline : FSelectView {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        setContentView(R.layout.item_tab_underline)

        configTextViewTitle()
            .setTextColorResIdNormal(R.color.res_text_gray_s)
            .setTextColorResIdSelected(R.color.res_main_color)
            .setSelected(false)
        configViewUnderline()
            .setVisibilityNormal(View.INVISIBLE)
            .setVisibilitySelected(View.VISIBLE)
            .setSelected(false)
    }

    open fun configTextViewTitle(): FTextViewSelectConfig {
        return configText(tv_title)
    }

    open fun configViewUnderline(): FViewSelectConfig {
        return config(view_underline)
    }

    fun getTextViewTitle() : TextView {
        return tv_title
    }
}