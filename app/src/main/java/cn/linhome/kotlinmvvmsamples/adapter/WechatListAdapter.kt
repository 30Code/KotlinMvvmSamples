package cn.linhome.kotlinmvvmsamples.adapter

import android.app.Activity
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.model.bean.Article
import cn.linhome.kotlinmvvmsamples.utils.GlideUtil
import cn.linhome.lib.adapter.FSimpleRecyclerAdapter
import cn.linhome.lib.adapter.viewholder.FRecyclerViewHolder
import org.jetbrains.anko.imageResource

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/7
 */
class WechatListAdapter(activity: Activity) : FSimpleRecyclerAdapter<Article>(activity) {

    private var mCallBack : CallBack? = null

    open fun setBackCall(callBack : CallBack) {
        this.mCallBack = callBack
    }

    override fun getLayoutId(parent: ViewGroup?, viewType: Int): Int = R.layout.item_wechat_list

    override fun onBindData(holder: FRecyclerViewHolder<Article>, position: Int, model: Article) {
//        val tv_article_title = holder.get<View>(R.id.tv_article_title) as TextView
//        val tv_article_author = holder.get<View>(R.id.tv_article_author) as TextView
//        val tv_article_date = holder.get<View>(R.id.tv_article_date) as TextView
//        val iv_like = holder.get<View>(R.id.iv_like) as ImageView
//        val tv_article_chapterName = holder.get<View>(R.id.tv_article_chapterName) as TextView
//        val iv_article_thumbnail = holder.get<View>(R.id.iv_article_thumbnail) as ImageView
//
//        tv_article_title.text = Html.fromHtml(model.title)
//        val authorStr = if (model.author.isNotEmpty()) model.author else model.shareUser
//        tv_article_author.text = authorStr
//        tv_article_date.text = model.niceDate
//        iv_like.imageResource = if (model.collect) R.drawable.ic_like else R.drawable.ic_like_not
//
//        val chapterName = when {
//            model.superChapterName.isNotEmpty() and model.chapterName.isNotEmpty() ->
//                "${model.superChapterName} / ${model.chapterName}"
//            model.superChapterName.isNotEmpty() -> model.superChapterName
//            model.chapterName.isNotEmpty() -> model.chapterName
//            else -> ""
//        }
//
//        tv_article_chapterName.text = chapterName
//        if (model.envelopePic.isNotEmpty()) {
//            iv_article_thumbnail.visibility = View.VISIBLE
//            GlideUtil.load(model.envelopePic)?.into(iv_article_thumbnail)
//        } else {
//            iv_article_thumbnail.visibility = View.GONE
//        }
//
//        iv_like.setOnClickListener {
//            mCallBack?.isCollectArticle(position, model)
//        }
//
//        holder.itemView.setOnClickListener {
//            notifyItemClickCallback(model, it)
//        }
    }

    interface CallBack {
        fun isCollectArticle(position: Int, model: Article)
    }
}