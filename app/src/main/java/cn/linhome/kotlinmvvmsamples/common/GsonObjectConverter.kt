package cn.linhome.kotlinmvpsamples.common

import cn.linhome.lib.cache.converter.IObjectConverter
import cn.linhome.lib.utils.json.FJson

/**
 * des:对象转换器(json)
 * Created by 30Code
 * on 2019/1/1
 */
class GsonObjectConverter : IObjectConverter {
    override fun objectToString(`object`: Any): String {
        return FJson.objectToJson(`object`) //对象转json
    }

    override fun <T> stringToObject(string: String, clazz: Class<T>): T {
        return FJson.jsonToObject(string, clazz) //json转对象
    }
}