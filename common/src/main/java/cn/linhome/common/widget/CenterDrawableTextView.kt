package cn.linhome.common.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/20
 */
open class CenterDrawableTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        CenterDrawableHelper.preDraw(this, canvas)
        super.onDraw(canvas)
    }
}