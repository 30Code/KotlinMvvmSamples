package cn.linhome.kotlinmvvmsamples.model.bean

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
data class Banner(val desc: String,
                  val id: Int,
                  val imagePath: String,
                  val isVisible: Int,
                  val order: Int,
                  val title: String,
                  val type: Int,
                  val url: String)
