package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class ItemList : SupportNode {
    companion object {
        const val type = "list_item"
    }

    override fun tag(node: Content): Any {
        return "li"
    }
}