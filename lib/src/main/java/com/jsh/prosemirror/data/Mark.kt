package com.jsh.prosemirror.data

import com.google.gson.annotations.SerializedName

data class Mark(
    @SerializedName("type")
    val type: String?,
    @SerializedName("attrs")
    val attrs: Attrs?
)