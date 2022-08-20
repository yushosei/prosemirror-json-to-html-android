package com.jsh.prosemirror.node

import com.jsh.prosemirror.data.Content

interface SupportNode {
    fun tag(node: Content): Any
}