package com.jsh.prosemirror

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.jsh.prosemirror.data.Content
import com.jsh.prosemirror.util.ProseMirrorJsonToHtml

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
}