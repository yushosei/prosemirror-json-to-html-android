package com.jsh.prosemirror.mark

import com.jsh.prosemirror.data.Mark

class Italic : SupportMark {
    companion object {
        const val type = "italic"
    }

    override fun tag(mark: Mark): String {
        return "em"
    }
}