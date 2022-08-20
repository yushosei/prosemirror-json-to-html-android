package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class Heading : SupportNode {
    companion object {
        const val type = "heading"
    }

    override fun tag(node: Content): Any {
        return ("h" + node.attrs?.level?.toString())
    }
}