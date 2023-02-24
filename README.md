# ProseMirror to HTML (Android)

(This package is based on [prosemirror-to-html](https://github.com/ueberdosis/prosemirror-to-html), which was originally written for PHP.)

Takes ProseMirror JSON and outputs HTML.



## Including in your project

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.yushosei:prosemirror-json-to-html-android:2.5'
}
```
And Add repositories
```gradle
allprojects {
        repositories {            
            maven { url "https://jitpack.io" }
        }
   }
```

## How to Use
### Serialization
```kotlin
data class YourDto(    
    @SerializedName("your_prosemirror_serialized_name")
    val content: Content // com.jsh.prosemirror.data.Content
)
```
### Simple Use
#### From Json string
```kotlin
val jsonString = "{
  "type": "doc",
  "content": [
    {
      "type": "paragraph",
      "content": [
        {
          "type": "text",
          "text": "Example Paragraph"
        }
      ]
    }
  ]
}"

// Use ProseMirrorWebView
proseMirrorWebView.loadProseMirrorJsonData(jsonString, "text/html", "UTF-8");

// Or Use WebView
val html = ProseMirrorJsonToHtml.render(jsonString)
webview.loadData(html, "text/html", "UTF-8");
```

#### From Content Class
```kotlin
val content: Content = Content(...)  

// Use ProseMirrorWebView
proseMirrorWebView.loadProseMirrorData(content, "text/html", "UTF-8");

// Or Use WebView
val html = ProseMirrorJsonToHtml.render(content)
webview.loadData(html, "text/html", "UTF-8");
```

### Append Custom Node
#### your node
```kotlin
class Iframe : SupportNode {
    companion object {
        const val type = "iframe"
    }

    override fun tag(node: Content): Any {
        return Tag(
            tag = "iframe",
            attrs = Attrs(
                src = node.attrs?.src,
                alt = node.attrs?.alt,
                title = node.attrs?.title,
            )
        )
    }
}
```
#### append 
```kotlin
ProseMirrorJsonToHtml.default.appendSupportNode(Iframe.type, Iframe())
```

### Use With URL FIX
```kotlin
// URL Extensions
fun String.toImgUrl() = "https://www.$this"
```

```kotlin
val content: Content = Content(...)  
// Add URL FIX Function
val parser = ProseMirrorJsonToHtml({
          hrefUrlFix = it.toImgUrl()
        }, {
            srcUrlFix = it.toImgUrl()
        })
        
// Use ProseMirrorWebView
proseMirrorWebView.loadProseMirrorData(content, "text/html", "UTF-8", parser);

// Or Use WebView
val html = parser.render(content)
webview.loadData(html, "text/html", "UTF-8");

// Or Use Singleton Global 
ProseMirrorJsonToHtml.default.hrefUrlFix = { it.toImgUrl() }
val html = ProseMirrorJsonToHtml.render(content)
webview.loadData(html, "text/html", "UTF-8");
```
## Supported Nodes

* Blockquote
* BulletList
* CodeBlock
* Heading
* ListItem
* OrderedList
* Paragraph

## Supported Marks

* Bold
* Code
* Italic
* Link

