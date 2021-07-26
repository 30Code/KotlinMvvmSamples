package cn.linhome.kotlinmvvmsamples.ui.webview

import android.os.Bundle
import android.webkit.WebView
import cn.linhome.common.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ActivityWebviewBinding
import cn.linhome.lib.webview.FWebViewHandler
import cn.linhome.lib.webview.FWebViewManager
import cn.linhome.lib.webview.client.FWebChromeClient
import cn.linhome.lib.webview.client.FWebViewClient
import java.net.HttpCookie

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {

    val URL = ""

    private var mWebViewClient: FWebViewClient? = null
    private var mWebChromeClient: FWebChromeClient? = null

    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun initActivity(savedInstanceState: Bundle?) {
        FWebViewManager.getInstance().setWebViewHandler(mWebViewHandler) //设置WebViewHandler

        mBinding.run {
            toolbar.run {
                title = getString(R.string.is_loading)
                setNavigationIcon(R.drawable.arrow_back)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }


            webview.webViewClient = getWebViewClient() //设置WebViewClient

            webview.webChromeClient = getWebChromeClient() //设置WebChromeClient

            webview.get(URL) //请求某个地址
        }

    }

    private fun getWebViewClient(): FWebViewClient {
        if (mWebViewClient == null) {
            mWebViewClient = object : FWebViewClient(this) {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return super.shouldOverrideUrlLoading(view, url)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    /**
                     * 页面加载完成后可以把webview的cookie同步到http框架
                     */
                    FWebViewManager.getInstance().synchronizeWebViewCookieToHttp(url)
                }
            }
        }
        return mWebViewClient!!
    }

    private fun getWebChromeClient(): FWebChromeClient? {
        if (mWebChromeClient == null) {
            mWebChromeClient = object : FWebChromeClient(this) {
                override fun onReceivedTitle(view: WebView, title: String) {
                    super.onReceivedTitle(view, title)
                    mBinding.toolbar.title = title//设置标题
                }
            }
            (mWebChromeClient as FWebChromeClient).setProgressBar(mBinding.pbProgress) //设置ProgressBar进度条
        }
        return mWebChromeClient
    }

    private val mWebViewHandler: FWebViewHandler = object : FWebViewHandler {
        override fun onInitWebView(webView: WebView) {
            /**
             * 每个FWebView被创建的时候都会回调此方法，可以做一些通用的初始化
             */
//            Log.i(TAG, "onInitWebView:$webView")
        }

        override fun getHttpCookieForUrl(url: String): List<HttpCookie>? {
            /**
             * 当FWebView加载某个url的时候会回调此方法，可以返回http框架保存的cookie给webview
             */
//            Log.i(TAG, "getHttpCookieForUrl:$url")
            return null
        }

        override fun synchronizeWebViewCookieToHttp(
            cookie: String,
            listCookie: List<HttpCookie>,
            url: String
        ) {
            /**
             * 当FWebViewManager的synchronizeWebViewCookieToHttp(url)方法被触发的时候会回调此方法，
             * 可以把webview的coookie存到http框架
             */
//            Log.i(TAG, "synchronizeWebViewCookieToHttp:$cookie")
        }
    }

    override fun onBackPressed() {
        if (mBinding.webview.canGoBack()) {
            mBinding.webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.webview.destroy()
    }
}