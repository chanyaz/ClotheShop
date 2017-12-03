package com.valgood.clotheshop.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.widget.TextView

import com.valgood.clotheshop.R

/**
 * Font Utils Class
 *
 * @version 1.0
 */
object CustomFontUtils {

    /**
     * Sets a font on a textview based on the custom com.my.package:font attribute
     * If the custom font attribute isn't found in the attributes nothing happens
     * @param textView
     * @param context
     * @param attrs
     */
    fun setCustomFont(textView: TextView, context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomFont)
        val font = a.getString(R.styleable.CustomFont_font)
        setCustomFont(textView, font, context)
        a.recycle()
    }

    /**
     * Sets a font on a textview
     * @param textView
     * @param font
     * @param context
     */
    fun setCustomFont(textView: TextView, font: String?, context: Context) {
        font?.let {
            FontCache[font, context]?.let {
                textView.typeface = it
            }
        }

    }

    fun formatTitleBar(context: Context, text: String, fontType: String) : SpannableString {
        return SpannableString(text).apply {
            setSpan(TypeFaceSpan(context, fontType), 0, length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(ContextCompat.getColor( context, R.color.colorAccent)),
                    0, length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
    }
}