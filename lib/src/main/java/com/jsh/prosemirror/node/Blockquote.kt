package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class Blockquote : SupportNode {
    companion object {
        const val type = "blockquote"
    }

    override fun tag(node: Content): Any {
        return "blockquote"
    }
}