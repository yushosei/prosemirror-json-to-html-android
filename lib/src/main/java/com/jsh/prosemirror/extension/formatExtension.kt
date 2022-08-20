package com.jsh.prosemirror.extension

import com.jsh.prosemirror.data.Content
import com.jsh.prosemirror.util.ProseMirrorJsonToHtml


fun Content.toHtml() = ProseMirrorJsonToHtml.render(this)

fun String.htmlEntities(): String {
    return replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#039;");
}

internal fun List<String>.join() =
    joinToString(separator = "") { it }