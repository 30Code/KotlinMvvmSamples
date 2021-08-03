package cn.linhome.common.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseRecyclerAdapter
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.common.widget.RequestStatusView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 *  des : BindingAdapter
 *  Created by 30Code
 *  date : 2021/7/18
 */
/**
 * 单独加载图片，没有 PlaceHolder
 */
@BindingAdapter("bind:img")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url).apply(RequestOptions.centerCropTransform())
        .into(view)
}

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

/**
 * 绑定图片加载
 * @param url 图片地址
 * @param placeholder 占位图
 * @param errorHolder 出错占位图
 */
@BindingAdapter(value = ["bind:imgUrl", "bind:placeHolder", "bind:error"], requireAll = false)
fun loadImageWithPlace(view : ImageView, url : String, placeholder: Drawable, errorHolder: Drawable) {
    Glide.with((view.context))
        .load(url)
        .apply(
            RequestOptions
                .centerCropTransform()
                .placeholder(placeholder)
                .error(errorHolder)
        ).into(view)
}

/**
 * 绑定 ViewPager 的一些属性
 */
@BindingAdapter("bind:limitOffset")
fun bindOffscreenPageLimit(viewPager: ViewPager, limit: Int) {
    viewPager.offscreenPageLimit = limit
}

@BindingAdapter(value = ["bind:reversed", "bind:transformer"], requireAll = false)
fun bindTransformer(viewPager: ViewPager, reversed: Boolean, transformer: ViewPager.PageTransformer) {
    viewPager.setPageTransformer(reversed, transformer)
}

@BindingAdapter(value = ["bind:currentItem", "bind:smoothScroll"])
fun bindCurrentItem(viewPager: ViewPager, current: Int, smoothScroll: Boolean) {
    viewPager.setCurrentItem(current, smoothScroll)
}

/**
 * viewPager2 setCurrentItem
 */
@BindingAdapter(value = ["bind:currentItem", "bind:smoothScroll"])
fun bindCurrentItem(viewPager2: ViewPager2, current: Int, smoothScroll: Boolean) {
    viewPager2.setCurrentItem(current, smoothScroll)
}

/**
 * viewPager2 limitOffset
 */
@BindingAdapter(value = ["bind:limitOffset"])
fun bindOffscreenPageLimit(viewPager2: ViewPager2, limit: Int) {
    viewPager2.offscreenPageLimit = limit
}

/**
 * 绑定 SwipeRefreshLayout 颜色，刷新状态，监听事件
 */
@BindingAdapter(
    value = ["bind:refreshColor", "bind:refreshState", "bind:refreshListener"],
    requireAll = false
)
fun bindRefreshColor(
    refreshLayout: SwipeRefreshLayout,
    color: Int,
    refreshState: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    refreshLayout.setColorSchemeResources(color)
    refreshLayout.isRefreshing = refreshState
    refreshLayout.setOnRefreshListener(listener)
}

@BindingAdapter("bind:refreshEnable")
fun bindRefreshEnable(refreshLayout: SwipeRefreshLayout, enable: Boolean) {
    refreshLayout.isEnabled = enable
}

/**
 * 绑定 paging3 adapter 点击
 */
@BindingAdapter(value = ["bind:pagingItemClick", "bind:pagingItemLongClick"], requireAll = false)
fun bindPagingItemClickV2(recyclerView: RecyclerView, listener: OnItemClickListener?, longListener: OnItemLongClickListener?) {
    val adapter = recyclerView.adapter ?: return

    val tarAdapter: BasePagingDataAdapter<*, *>? = when (adapter) {
        is BasePagingDataAdapter<*, *> -> adapter
        is ConcatAdapter -> findBasePagingAdapterInConcatAdapter(adapter)
        else -> null
    }

    tarAdapter?.run {
        itemListener = listener
        itemLongClickListener = longListener
    } ?: return
}

private fun findBasePagingAdapterInConcatAdapter(mergeAdapter: ConcatAdapter): BasePagingDataAdapter<*, *>? {
    val adapterList = mergeAdapter.adapters

    for (i in adapterList.indices) {
        val adapter = adapterList[i]
        if (adapter is BasePagingDataAdapter<*, *>) return adapter
    }
    return null
}

/**
 * 绑定 RecyclerView 的点击事件
 * @param listener 点击事件，[OnItemClickListener]
 */
@BindingAdapter(value = ["bind:listItemClick", "bind:listItemLongClick"], requireAll = false)
fun bindRecyclerItemClick(recyclerView: RecyclerView, listener: OnItemClickListener?, longListener: OnItemLongClickListener?) {
    val adapter = recyclerView.adapter as? BaseRecyclerAdapter<*> ?: return
    adapter.itemListener = listener
    adapter.itemLongListener = longListener
}

/**
 * 绑定 recyclerView 分割线
 */
@BindingAdapter("bind:divider")
fun bindRecyclerDivider(recyclerView: RecyclerView, decor: RecyclerView.ItemDecoration) {
    recyclerView.addItemDecoration(decor)
}

/**
 * recyclerView 是否固定高度
 */
@BindingAdapter("bind:hasFixedSize")
fun bindRecyclerHasFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean) {
    recyclerView.setHasFixedSize(hasFixedSize)
}

/**
 * recyclerView 滚动到指定 position，并指定偏移量
 */
@BindingAdapter(value = ["bind:scrollTo", "bind:offset"])
fun bindScrollTo(recyclerView: RecyclerView, position: Int, offset: Int) {
    recyclerView.layoutManager.let {
        when (it) {
            is LinearLayoutManager -> it.scrollToPositionWithOffset(position, offset)

            is GridLayoutManager -> it.scrollToPositionWithOffset(position, offset)

            is StaggeredGridLayoutManager -> it.scrollToPositionWithOffset(position, offset)
        }
    }
}

@BindingAdapter("bind:scrollListener")
fun bindRecyclerScrollListener(recyclerView: RecyclerView, l: RecyclerView.OnScrollListener) {
    recyclerView.addOnScrollListener(l)
}

@BindingAdapter("bind:gesture")
fun bindViewGesture(view: View, doubleClickListener: DoubleClickListener) {
    view.setOnTouchListener(doubleClickListener)
}

/**
 * 错误处理绑定
 */
@BindingAdapter(value = ["bind:requestStatusCode", "bind:errorReload"], requireAll = false)
fun bindRequestStatus(statusView: RequestStatusView, requestStatusCode: RequestStatusCode?, errorReload: ErrorReload?) {
    statusView.injectRequestStatus(requestStatusCode ?: RequestStatusCode.Succeed)
    statusView.errorReload = errorReload
}

/**
 * 绑定 EditText 一些属性
 */
@BindingAdapter("bind:editAction")
fun bindEditAction(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(editorActionListener)
}


