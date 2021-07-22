package cn.linhome.kotlinmvvmsamples.ui.main

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.linhome.common.Constant
import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.UserData
import cn.linhome.lib.utils.context.FPreferencesUtil
import kotlinx.coroutines.flow.flow
import retrofit2.Response

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

    fun login(username : String, password : String) = flow {
        emit(repository.login(username, password))
    }

    fun register(username: String, password: String, repass: String) = flow {
        emit(repository.register(username, password, repass))
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

    /**
     * 存储用户信息
     */
    fun saveUserInfo(info : Response<BaseResultData<UserData>>) {
        if (info.body()?.errorCode == 0) {
            val cookies = StringBuilder()

            info.headers()
                .filter { TextUtils.equals(it.first, "Set-Cookie") }
                .forEach { cookies.append(it.second).append(";") }

            val strCookie = if (cookies.endsWith(";")) {
                cookies.substring(0, cookies.length - 1)
            } else {
                cookies.toString()
            }

            FPreferencesUtil.putString(Constant.DiskKey.COOKIE, strCookie)
            FPreferencesUtil.putInt(Constant.DiskKey.USERID, info.body()?.data?.id ?: 0)
            FPreferencesUtil.putString(Constant.DiskKey.USERNAME, info.body()?.data?.nickname ?: "")
        }
    }
}