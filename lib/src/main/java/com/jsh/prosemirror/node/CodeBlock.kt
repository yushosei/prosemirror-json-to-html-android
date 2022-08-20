package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content
import com.jsh.prosemirror.data.Tag

class CodeBlock : SupportNode {
    companion object {
        const val type = "code_block"
    }

    override fun tag(node: Content): Any {
        return listOf(
            Tag(tag = "pre"),
            Tag(tag = "code")
        )
    }
}