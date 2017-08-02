package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.util.AttributeSet

import com.valgood.clotheshop.utils.CustomFontUtils

/**
 * Custom class to load different fonts that the ones provided by android
 */
class CustomFontTextView : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        CustomFontUtils.setCustomFont(this, context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        CustomFontUtils.setCustomFont(this, context, attrs)
    }
}
