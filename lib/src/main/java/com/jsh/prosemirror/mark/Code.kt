package com.jsh.prosemirror.mark

import com.jsh.prosemirror.data.Mark

class Code : SupportMark {
    companion object {
        const val type = "code"
    }

    override fun tag(mark: Mark): String {
        return "code"
    }
}