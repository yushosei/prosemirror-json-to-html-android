package com.jsh.prosemirror

import com.google.gson.Gson
import com.jsh.prosemirror.data.Attrs
import com.jsh.prosemirror.data.Content
import com.jsh.prosemirror.data.Tag
import com.jsh.prosemirror.extension.htmlEntities
import com.jsh.prosemirror.extension.join
import com.jsh.prosemirror.mark.*
import com.jsh.prosemirror.node.*

class ProseMirrorJsonToHtml(
    var hrefUrlFix: (String) -> String = { it },
    var srcUrlFix: (String) -> String = { it }
) {
    companion object {
        val default: ProseMirrorJsonToHtml by lazy { ProseMirrorJsonToHtml() }
        private val gson: Gson by lazy { Gson() }

        fun render(content: Content) = default.render(content)
        fun render(json: String) = default.render(json)

        private val supportMarks = mutableMapOf(
            Bold.type to Bold(),
            Code.type to Code(),
            Italic.type to Italic(),
            Link.type to Link()
        )

        private val supportNodes = mutableMapOf(
            Blockquote.type to Blockquote(),
            BulletList.type to BulletList(),
            CodeBlock.type to CodeBlock(),
            Heading.type to Heading(),
            Image.type to Image(),
            ItemList.type to ItemList(),
            OrderedList.type to OrderedList(),
            Paragraph.type to Paragraph(),
            // Iframe.type to Iframe()
        )
    }

    fun appendSupportMark(type: String, mark: SupportMark) {
        supportMarks[type] = mark
    }

    fun appendSupportNode(type: String, node: SupportNode) {
        supportNodes[type] = node
    }

    fun render(json: String): String {
        return render(gson.fromJson(json, Content::class.java))
    }

    private fun Any?.toTagArray(): List<*> {
        this?.let {
            return if (it is List<*>)
                it
            else
                listOf(this)
        } ?: run {
            return listOf(null)
        }
    }

    private fun renderAttrTag(attrs: Attrs?): String {
        if (attrs == null)
            return ""

        var result = ""

        attrs.href?.let {
            result += " href=\"${hrefUrlFix(it)}\""
        }
        attrs.level?.let {
            result += " level=\"$it\""
        }
        attrs.alt?.let {
            result += " alt=\"$it\""
        }
        attrs.src?.let {
            result += " src=\"${srcUrlFix(it)}\""
        }
        attrs.title?.let {
            result += " title=\"$it\""
        }
        attrs.order?.let {
            result += " order=\"$it\""
        }

        return result
    }

    private fun renderOpeningTag(tags: Any): String {
        val temp = tags.toTagArray()
        if (temp.isEmpty())
            return ""

        return temp.map {
            it?.let {
                when (it) {
                    is String -> "<$it>"
                    is Tag -> {
                        "<${it.tag}${renderAttrTag(it.attrs)}>"
                    }
                    else -> ""
                }
            } ?: run { "" }
        }.join()
    }

    private fun renderClosingTag(tags: Any): String {
        var temp = tags.toTagArray()
        if (temp.isEmpty())
            return ""

        temp = temp.reversed()

        return temp.map {
            it?.let {
                when (it) {
                    is String -> "</$it>"
                    is Tag -> "</${it.tag}>"
                    else -> ""
                }
            } ?: run { "" }
        }.join()
    }

    private fun renderNode(node: Content): String {
        val html = ArrayList<String>()

        node.marks?.let {
            it.forEach { mark ->
                supportMarks[mark.type]?.let { target ->
                    html.add(renderOpeningTag(target.tag(mark)))
                }
            }
        }

        node.type?.let {
            supportNodes[it]?.let { target ->
                html.add(renderOpeningTag(target.tag(node)))
            }
        }

        node.content?.let {
            it.forEach { node ->
                html.add(renderNode(node))
            }
        } ?: run {
            node.text?.let {
                html.add(it.htmlEntities())
            }
        }

        node.type?.let {
            supportNodes[it]?.let { target ->
                if (target is Image)
                    ""
                else
                    html.add(renderClosingTag(target.tag(node)))
            }
        }

        node.marks?.let {
            it.reversed().forEach { mark ->
                supportMarks[mark.type]?.let { target ->
                    html.add(renderClosingTag(target.tag(mark)))
                }
            }
        }
        return html.join()
    }

    fun render(content: Content): String {
        val html = ArrayList<String>()

        content.content?.forEach {
            html.add(renderNode(it))
        }

        return html.join()
    }
}