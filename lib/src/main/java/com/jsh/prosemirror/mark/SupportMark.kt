package com.jsh.prosemirror.mark

import com.jsh.prosemirror.data.Mark

interface SupportMark {
    fun tag(mark: Mark): Any
}