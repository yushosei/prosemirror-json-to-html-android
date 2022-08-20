package com.jsh.prosemirror.mark

import com.jsh.prosemirror.data.Mark

class Bold : SupportMark {
    companion object {
        const val type = "bold"
    }

    override fun tag(mark: Mark): String {
        return "strong"
    }
}