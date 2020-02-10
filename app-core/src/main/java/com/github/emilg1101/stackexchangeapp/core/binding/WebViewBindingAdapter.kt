package com.github.emilg1101.stackexchangeapp.core.binding

import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.emilg1101.stackexchangeapp.core.R
import com.github.rjeschke.txtmark.Processor

@BindingAdapter("app:loadMarkdown")
fun loadMarkdown(webView: WebView, markdown: String?) {
    webView.loadData(Processor.process(markdown ?: ""), "text/html", "UTF-8")
    webView.setBackgroundColor(ContextCompat.getColor(webView.context, R.color.colorPrimary))
}

@BindingAdapter("app:loadHtml")
fun loadHtml(webView: WebView, html: String?) {
    webView.loadData(html ?: "", "text/html", "UTF-8")
    webView.setBackgroundColor(ContextCompat.getColor(webView.context, R.color.colorPrimary))
}
