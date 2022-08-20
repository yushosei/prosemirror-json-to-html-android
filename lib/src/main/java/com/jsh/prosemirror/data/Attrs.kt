package com.jsh.prosemirror.data

import com.google.gson.annotations.SerializedName

data class Attrs(
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("alt")
    val alt: Any? = null,
    @SerializedName("src")
    val src: String? = null,
    @SerializedName("href")
    val href: String? = null,
    @SerializedName("title")
    val title: Any? = null,
    @SerializedName("level")
    val level: Int? = null,
)