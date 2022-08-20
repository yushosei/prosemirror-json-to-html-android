package com.jsh.prosemirror.mark

import com.jsh.prosemirror.data.Attrs
import com.jsh.prosemirror.data.Mark
import com.jsh.prosemirror.data.Tag

class Link : SupportMark {
    companion object {
        const val type = "link"
    }

    override fun tag(mark: Mark): Any {
        return Tag(tag = "a", attrs = Attrs(href = mark.attrs?.href))
    }
}