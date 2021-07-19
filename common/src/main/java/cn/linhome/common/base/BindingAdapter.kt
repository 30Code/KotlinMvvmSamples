package cn.linhome.common.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 *  des : BindingAdapter
 *  Created by 30Code
 *  date : 2021/7/18
 */

/**
 * 绑定本地圆形头像
 */
@BindingAdapter("bind:circleImg")
fun bindCircleImage(imageView : ImageView, imgRes : Drawable) {
    Glide.with(imageView.context)
        .load(imgRes)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(360)))
        .into(imageView)
}