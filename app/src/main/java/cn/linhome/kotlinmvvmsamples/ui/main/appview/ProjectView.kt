package cn.linhome.kotlinmvvmsamples.ui.main.appview

import android.content.Context
import android.util.AttributeSet
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseAppView

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/25
 */
class ProjectView : BaseAppView {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context?) : super(context) {}

    override fun onCreateContentView(): Int = R.layout.view_project

    override fun onBaseInit() {
        super.onBaseInit()
    }

}