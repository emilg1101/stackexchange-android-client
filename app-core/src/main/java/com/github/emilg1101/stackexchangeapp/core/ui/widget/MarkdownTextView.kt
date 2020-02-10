package com.github.emilg1101.stackexchangeapp.core.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.AttributeSet
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class MarkdownTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr), Html.ImageGetter {

    var markdown: String = ""
        set(value) {
            field = value
            val html = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY, this, null)
            } else {
                Html.fromHtml(value, this, null)
            }
            text = html
        }

    override fun getDrawable(source: String?): Drawable {
        val placeHolder = BitmapDrawablePlaceHolder()
        Glide.with(context).load(source).into(object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                placeHolder.drawable = resource
            }
        })
        return placeHolder
    }

    inner class BitmapDrawablePlaceHolder : BitmapDrawable() {

        var drawable: Drawable? = null
            set(value) {
                field = value
                val width = drawable?.intrinsicWidth ?: 0
                val height = drawable?.intrinsicHeight ?: 0
                drawable?.setBounds(0, 0, width, height)
                setBounds(0, 0, width, height)
                this@MarkdownTextView.text = this@MarkdownTextView.text
            }

        override fun draw(canvas: Canvas) {
            if (drawable != null) {
                drawable?.draw(canvas)
            }
        }
    }
}
