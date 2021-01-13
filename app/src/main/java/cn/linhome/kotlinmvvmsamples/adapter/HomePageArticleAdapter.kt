package cn.linhome.kotlinmvvmsamples.adapter

import cn.linhome.kotlinmvvmsamples.BR
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.constants.Constant.APP_START
import cn.linhome.kotlinmvvmsamples.model.bean.Article
import cn.linhome.kotlinmvvmsamples.utils.Timer

class HomePageArticleAdapter(layoutResId : Int = R.layout.item_home_page) : BaseBindAdapter<Article>(layoutResId, BR.viewModel) {
    private var showStar = true

    fun showStar(showStar: Boolean) {
        this.showStar = showStar
    }

    override fun convert(helper: BindViewHolder, item: Article) {
        super.convert(helper, item)
        helper.addOnClickListener(R.id.articleStar)
        if (showStar) helper.setImageResource(R.id.articleStar, if (item.collect) R.drawable.timeline_like_pressed else R.drawable.timeline_like_normal)
        else helper.setVisible(R.id.articleStar, false)

        helper.setText(R.id.articleAuthor, if (item.author.isBlank()) "分享者: ${item.shareUser}" else item.author)
        Timer.stop(APP_START)
    }
}