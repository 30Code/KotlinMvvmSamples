package cn.linhome.kotlinmvvmsamples.dialog.common

import android.app.Activity
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.dialog.impl.FDialog

open class AppBaseDialog(activity: Activity?) : FDialog(activity, R.style.dialogBase)