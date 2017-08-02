package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.valgood.clotheshop.R

/**
 * Custom tab layout with custom fonts
 */

class CustomTabLayout : TabLayout {
    private var mTypeface: Typeface? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mTypeface = Typeface.createFromAsset(context.assets, context.getString(R.string.roboto_medium))
    }

    override fun addTab(tab: TabLayout.Tab, setSelected: Boolean) {
        super.addTab(tab, setSelected)
        addTab(tab)
    }

    override fun addTab(tab: TabLayout.Tab, position: Int, setSelected: Boolean) {
        super.addTab(tab, position, setSelected)
        addTab(tab)
    }

    override fun addTab(tab: TabLayout.Tab) {
        val mainView = getChildAt(0) as ViewGroup
        val tabView = mainView.getChildAt(tab.position) as ViewGroup

        val tabChildCount = tabView.childCount
        for (i in 0..tabChildCount - 1) {
            val tabViewChild = tabView.getChildAt(i)
            (tabViewChild as? TextView)?.setTypeface(mTypeface, Typeface.BOLD)
        }
    }

}