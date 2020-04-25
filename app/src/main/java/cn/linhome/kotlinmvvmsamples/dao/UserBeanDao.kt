package cn.linhome.kotlinmvvmsamples.dao

import cn.linhome.kotlinmvvmsamples.constants.Constant
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import cn.linhome.lib.cache.FDisk

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/25
 */
object UserBeanDao {

    var mLoginData : LoginData? = null

    fun insertOrUpdate(loginData : LoginData?): Boolean {
        return FDisk.openInternalCache(Constant.USER_INFO).cacheObject().put(loginData);
    }

    fun query() : LoginData? {
        mLoginData = FDisk.openInternalCache(Constant.USER_INFO).cacheObject().get(LoginData::class.java)
        return mLoginData
    }

    fun delete() {
        FDisk.openInternalCache(Constant.USER_INFO).cacheObject().remove(LoginData::class.java)
    }

}