package cn.linhome.kotlinmvpsamples.common

import cn.linhome.lib.cache.converter.IEncryptConverter
import cn.linhome.lib.utils.encrypt.FAESUtil

/**
 * des:加解密转换器
 * Created by 30Code
 * on 2019/1/1
 */
class GlobalEncryptConverter : IEncryptConverter {
    override fun encrypt(content: String): String {
        return FAESUtil.encrypt(content) //加密
    }

    override fun decrypt(content: String): String {
        return FAESUtil.decrypt(content) //解密
    }
}