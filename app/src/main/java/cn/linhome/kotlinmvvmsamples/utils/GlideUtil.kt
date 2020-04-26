package cn.linhome.kotlinmvvmsamples.utils

import android.graphics.drawable.Drawable
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.utils.context.FContext
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/8
 */
object GlideUtil {

    /**
     * @return Glide默认配置
     */
    fun getDefaultOptions(): RequestOptions {
        return RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //只缓存转换后的图片
            .dontAnimate()
    }

    /**
     * @return Glide通常配置
     */
    fun getNormalOptions(): RequestOptions? {
        return getDefaultOptions()
            .placeholder(R.drawable.bg_image_loading)
            .error(R.drawable.bg_image_loading)
    }

    /**
     * placeholder和error不会被处理成圆角
     *
     * @return Glide圆角配置
     */
    fun getRoundedCornersOptions(): RequestOptions? {
        return getDefaultOptions()
            .placeholder(R.drawable.bg_image_loading)
            .error(R.drawable.bg_image_loading)
            .transforms(
                CenterCrop(),
                RoundedCorners(
                    FContext.get().resources.getDimension(R.dimen.res_corner_s).toInt()
                )
            )
    }

    /**
     * placeholder和error不会被处理成圆角
     *
     * @return Glide圆形配置
     */
    fun getCircleOptions(): RequestOptions? {
        return getDefaultOptions()
            .placeholder(R.drawable.bg_image_loading)
            .error(R.drawable.bg_image_loading)
            .transforms(CenterCrop(), CircleCrop())
    }

    /**
     * 通常调用方法
     *
     * @param model String, byte[], File, Integer, Uri
     * @param <T>
    </T> */
    fun <T> load(model: T): RequestBuilder<Drawable?>? {
        return load(model, getNormalOptions())
    }

    fun <T> load(
        model: T,
        requestOptions: RequestOptions?
    ): RequestBuilder<Drawable?>? {
        return Glide.with(FContext.get()).load(model).apply(requestOptions!!)
    }

    //---------以下为扩展方法------------

    //---------以下为扩展方法------------
    /**
     * 加载用户头像方法
     *
     * @param model String, byte[], File, Integer, Uri
     * @param <T>
    </T> */
    fun <T> loadHeadImage(model: T): RequestBuilder<Drawable?>? {
        val options: RequestOptions = getDefaultOptions()
            .placeholder(R.drawable.bg_head_image_loading)
            .error(R.drawable.bg_head_image_loading)
        return Glide.with(FContext.get()).load(model).apply(options)
    }

}