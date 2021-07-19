package cn.linhome.common.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import cn.linhome.common.R
import cn.linhome.common.base.BaseDialogFragment
import cn.linhome.common.databinding.DialogLoadingBinding

/**
 *  des : LoadingDialog
 *  Created by 30Code
 *  date : 2021/7/18
 */
class LoadingDialog : BaseDialogFragment<DialogLoadingBinding>() {

    override fun layoutId(): Int = R.layout.dialog_loading

    override fun initDialog(view: View, savedInstanceState: Bundle?) {
        isCancelable = false

        (mBinding.loading.drawable as? AnimationDrawable)?.start()
    }

    override fun dialogFragmentAnim() = R.style.DialogAlterWithNoAnimation

    override fun dialogFragmentAttributes() = dialog?.window?.attributes?.apply {
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        gravity = Gravity.CENTER
    }
}