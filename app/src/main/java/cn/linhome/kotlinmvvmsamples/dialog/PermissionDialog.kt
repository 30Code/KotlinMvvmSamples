package cn.linhome.kotlinmvvmsamples.dialog

import android.app.Activity
import android.view.View
import cn.linhome.kotlinmvpsamples.granted.PermissionCompat
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.dialog.common.AppBaseDialog
import kotlinx.android.synthetic.main.dialog_permission.*

/**
 * des:权限未开启提示
 * Created by 30Code
 * on 2020/4/24
 */
class PermissionDialog(activity: Activity?) : AppBaseDialog(activity) {

    private var mPermissionCallBack : PermissionCallBack? = null

    init {
        init()
    }

    fun setPermissionCallBack(permissionCallBack : PermissionCallBack) {
        this.mPermissionCallBack = permissionCallBack
    }

    private fun init() {
        setContentView(R.layout.dialog_permission)
        tv_cancel.setOnClickListener(this)
        tv_setting.setOnClickListener(this)
    }

    fun setPermissionTip(tipContent: String) {
        tv_permission_tip.text = tipContent
    }

    override fun onClick(v: View) {
        super.onClick(v)
        if (mPermissionCallBack != null) {
            if (v === tv_cancel) {
                dismiss()
                mPermissionCallBack!!.onCancel()
            } else if (v === tv_setting) {
                dismiss()
                PermissionCompat.start(getContext(), false);
                mPermissionCallBack!!.permissionSetting()
            }
        }
    }

    interface PermissionCallBack {
        /**
         * 取消
         */
        fun onCancel()

        /**
         * 权限设置
         */
        fun permissionSetting()
    }
}