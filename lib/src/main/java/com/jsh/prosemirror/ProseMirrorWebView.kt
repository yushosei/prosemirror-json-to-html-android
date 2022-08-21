package com.jsh.prosemirror

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.jsh.prosemirror.data.Content

class ProseMirrorWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {

    fun loadProseMirror(
        content: Content,
        mimeType: String,
        encoding: String,
        parser: ProseMirrorJsonToHtml = ProseMirrorJsonToHtml.default
    ) {
        loadData(parser.render(content), mimeType, encoding)
    }

    fun loadProseMirror(
        json: String,
        mimeType: String,
        encoding: String,
        parser: ProseMirrorJsonToHtml = ProseMirrorJsonToHtml.default
    ) {
        loadData(parser.render(json), mimeType, encoding)
    }
}