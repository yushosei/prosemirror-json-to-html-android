package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class Paragraph : SupportNode {
    companion object {
        const val type = "paragraph"
    }

    override fun tag(node: Content): Any {
        return "p"
    }
}