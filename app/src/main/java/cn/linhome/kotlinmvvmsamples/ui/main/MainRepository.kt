package cn.linhome.kotlinmvvmsamples.ui.main

import cn.linhome.common.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : MainRepository
 *  Created by 30Code
 *  date : 2021/7/19
 */
class MainRepository(val api : ApiService) {

    /**
     * 获得个人积分
     */
    suspend fun getCoins() = withContext(Dispatchers.IO) {
//        val cookie = FPreferencesUtil.getString(Constant.DiskKey.COOKIE, "")
        api.fetchUserCoins().data
    }

    /**
     * 登出
     */
    suspend fun loginOut() = withContext(Dispatchers.IO) {
        api.loginOut()
    }
}