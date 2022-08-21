package com.jsh.prosemirror.extension

fun String.htmlEntities(): String {
    return replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&#039;", "'");
}

internal fun List<String>.join() =
    joinToString(separator = "") { it }