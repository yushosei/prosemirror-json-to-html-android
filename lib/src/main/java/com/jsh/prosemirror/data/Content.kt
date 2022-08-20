package com.jsh.prosemirror.data

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("content")
    val content: List<Content>? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("attrs")
    val attrs: Attrs? = null,
    @SerializedName("marks")
    val marks: List<Mark>? = null,
)