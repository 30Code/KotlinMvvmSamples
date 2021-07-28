package cn.linhome.login.ui

import cn.linhome.login.api.LoginApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : MainRepository
 *  Created by 30Code
 *  date : 2021/7/19
 */
class LoginRepository(val api : LoginApiService) {

    /**
     * 登录
     */
    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO) {
        api.login(username, password)
    }

    /**
     * 注册
     */
    suspend fun register(username: String, password: String, repass: String) = withContext(Dispatchers.IO) {
        api.register(username, password, repass)
    }

}