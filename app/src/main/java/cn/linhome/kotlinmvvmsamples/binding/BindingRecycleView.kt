package cn.linhome.kotlinmvvmsamples.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.linhome.kotlinmvvmsamples.view.SpaceItemDecoration

@BindingAdapter("itemTopPadding", "itemLeftPadding", "itemBottomPadding", "itemRightPadding",requireAll = false)
fun RecyclerView.addItemPadding(top: Int = 0, left: Int = 0, bottom: Int = 0, right: Int = 0) {
    addItemDecoration(SpaceItemDecoration(top, left, bottom, right))
}

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}