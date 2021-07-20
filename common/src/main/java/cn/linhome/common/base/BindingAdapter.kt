package cn.linhome.common.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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
    value = ["bind:refreshColor", "bind:refreshState", "bind:refreshListener", "bind:refreshEnable"],
    requireAll = false
)
fun bindSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout, color: Int, refreshState: Boolean,
                           listener: SwipeRefreshLayout.OnRefreshListener, enable: Boolean) {
    refreshLayout.setColorSchemeResources(color)
    refreshLayout.isRefreshing = refreshState
    refreshLayout.setOnRefreshListener(listener)
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

