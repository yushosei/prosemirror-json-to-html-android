package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

class BulletList : SupportNode {
    companion object {
        const val type = "bullet_list"
    }

    override fun tag(node: Content): Any {
        return "ul"
    }
}