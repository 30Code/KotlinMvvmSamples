package cn.linhome.kotlinmvvmsamples.adapter

import cn.linhome.kotlinmvvmsamples.BR
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.model.bean.Article

class HomePageArticleAdapter(layoutResId : Int = R.layout.item_home_page) : BaseBindAdapter<Article>(layoutResId, BR.article) {

    override fun convert(helper: BindViewHolder, item: Article) {
        super.convert(helper, item)
        helper.addOnClickListener(R.id.articleStar)
    }
}