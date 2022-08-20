package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class OrderedList : SupportNode {
    companion object {
        const val type = "ordered_list"
    }

    override fun tag(node: Content): Any {
        return "ol"
    }
}