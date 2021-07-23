package com.hi.dhl.plugin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/15
 *     desc  : 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 * </pre>
 */

object Versions {
    val appcompat = "1.3.0"
    val coreKtx = "1.6.0"
    val constraintlayout = "2.0.4"
    val compose_version = "1.0.0-rc01"

    val activityCompose = "1.3.0-rc01"
    val lifecycleExtensions = "2.2.0"
    val lifecycleViewmodelKtx = "2.3.1"
    val lifecycleViewmodelCompose = "1.0.0-alpha07"

    val navigation = "2.3.5"
    
    val retrofit = "2.9.0"
    val okhttp3 = "5.0.0-alpha.2"

    val glide = "4.12.0"

    val paging = "3.1.0-alpha02"
    val timber = "4.7.1"
    val kotlin = "1.5.10"
    val koin = "2.1.6"
    val work = "2.2.0"
    val room = "2.4.0-alpha03"
    val cardview = "1.0.0"
    val viewpager2 = "1.1.0-alpha01"
    val recyclerview = "1.2.1"
    val swiperefreshlayout = "1.1.0"
    val fragment = "1.2.1"
    val anko = "0.10.8"

    val junit = "4.12"
    val junitExt = "1.1.1"
    val espressoCore = "3.2.0"
    val jDatabinding = "1.0.1"
}

object AndroidX {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    
    val composeUi = "androidx.compose.ui:ui:${Versions.compose_version}"
    val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
    val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
    val composeFoundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose_version}"
    val composeMaterial = "androidx.compose.material:material:${Versions.compose_version}"
    val composeMaterialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose_version}"
    val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
    val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose_version}"
    val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose_version}"
    val composeRuntimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"

    val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
    val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewmodelCompose}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"

    val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"




    val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    val workTesting = "androidx.work:work-testing:${Versions.work}"

}

object Google {
    val flexbox = "com.google.android:flexbox:2.0.1"
    val material = "com.google.android.material:material:1.5.0-alpha01"
}

object Room {
    val runtime = "androidx.room:room-runtime:${Versions.room}"
    val compiler = "androidx.room:room-compiler:${Versions.room}"
    val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Fragment {
    val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Kt {
    val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Koin {
    val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
    val android = "org.koin:koin-android:${Versions.koin}"
    val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val androidxScope = "org.koin:koin-androidx-scope:$${Versions.koin}"
}

object Anko {
    val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp3lLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Depend {

    val junit = "junit:junit:${Versions.junit}"
    val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val jDatabinding = "com.hi-dhl:jdatabinding:${Versions.jDatabinding}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Other {
    val banner = "com.youth.banner:banner:2.1.0"
    val fab =  "com.github.clans:fab:1.6.4"
    val arouter = "com.alibaba:arouter-api:1.5.1"
    val autosize = "me.jessyan:autosize:1.2.1"
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"
}

object Librarys  {
    val res = "com.github.30Code:res:1.0.5.v2"
    val utils = "com.github.30Code:utils:1.0.4.v2"
    val utilsextend = "com.github.30Code:utilsextend:1.0.4"
}

