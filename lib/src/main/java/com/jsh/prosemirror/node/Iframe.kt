package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Attrs
import com.jsh.prosemirror.data.Content
import com.jsh.prosemirror.data.Tag

class Iframe : SupportNode {
    companion object {
        const val type = "iframe"
    }

    override fun tag(node: Content): Any {
        return Tag(
            tag = "iframe",
            attrs = Attrs(
                src = node.attrs?.src,
                alt = node.attrs?.alt,
                title = node.attrs?.title,
            )
        )
    }
}