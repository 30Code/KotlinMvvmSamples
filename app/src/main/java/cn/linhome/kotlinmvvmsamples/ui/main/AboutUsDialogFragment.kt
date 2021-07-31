package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import cn.linhome.common.base.BaseDialogFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.DialogAboutUsBinding

/**
 *  des : 关于我们
 *  Created by 30Code
 *  date : 2021/7/31
 */
class AboutUsDialogFragment : BaseDialogFragment<DialogAboutUsBinding>() {

    var aboutUsHandler: ((String) -> Unit)? = null

    override fun layoutId(): Int = R.layout.dialog_about_us

    override fun initDialog(view: View, savedInstanceState: Bundle?) {
        mBinding.run {
            holder = this@AboutUsDialogFragment

            movementMethod = LinkMovementMethod.getInstance()

            kSpan = SpannableStringBuilder("30Code").apply {
                setSpan(object : ClickableSpan() {
                    override fun onClick(p0: View) {
                        aboutUsHandler?.invoke("https://github.com/30Code")
                        dialog?.dismiss()
                    }
                }, 0, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

                append(": 逗比码农一枚，主要做 Android，会写点 Flutter，传说中的样样通样样松~~")
            }

        }
    }

    fun ensure(view: View) {
        dialog?.dismiss()
    }
}