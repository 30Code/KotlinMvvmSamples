package cn.linhome.kotlinmvvmsamples.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.linhome.common.constant.Constant
import cn.linhome.lib.utils.context.FPreferencesUtil
import kotlinx.coroutines.flow.flow

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/19
 */
class MainViewModel(val repository: MainRepository) : ViewModel() {

    val hasLogin = MutableLiveData<Boolean>()

    init {
        hasLogin.value = FPreferencesUtil.getInt(Constant.DiskKey.USERID, 0) > 0
    }

    fun getCoinInfo() = flow {
        emit(repository.getCoins())
    }

    fun loginOut() = flow {
        emit(repository.loginOut())
    }

    /**
     * 清除用户信息
     */
    fun clearUserInfo() {
        FPreferencesUtil.putInt(Constant.DiskKey.USERID, 0)
        FPreferencesUtil.putString(Constant.DiskKey.USERNAME, "")
        FPreferencesUtil.putString(Constant.DiskKey.COOKIE, "")
    }

}