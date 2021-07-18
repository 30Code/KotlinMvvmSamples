package cn.linhome.common.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.linhome.common.base.SingleLiveEvent

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/18
 */
class AppViewModel : ViewModel() {
    val showLoadingProgress = SingleLiveEvent<Boolean>()

    val reloadHomeData = MutableLiveData<Boolean>()

    val reloadCollectWebSite = SingleLiveEvent<Boolean>()

    val needUpdateTodoList = SingleLiveEvent<Boolean>()

    fun showLoading() = showLoadingProgress.postValue(true)

    fun dismissLoading() = showLoadingProgress.postValue(false)
}