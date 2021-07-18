package cn.linhome.common.vm

import androidx.lifecycle.ViewModel
import cn.linhome.common.base.SingleLiveEvent

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/18
 */
class AppViewModel : ViewModel() {
    val showLoadingProgress = SingleLiveEvent<Boolean>()

}