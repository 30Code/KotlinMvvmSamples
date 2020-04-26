package cn.linhome.kotlinmvvmsamples.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.select.config.FImageViewSelectConfig
import cn.linhome.lib.select.view.FSelectView
import kotlinx.android.synthetic.main.view_tab_main_menu.view.*

/**
 * 首页底部菜单tab
 */
class TabMainMenuView : FSelectView {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_tab_main_menu, this, true)
    }

    fun configImage(): FImageViewSelectConfig {
        return configImage(iv_tab_image)
    }
}