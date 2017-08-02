package com.valgood.clotheshop.adapter

/**
 * Adapter to hold option in the main tab
 */

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

import com.valgood.clotheshop.R
import com.valgood.clotheshop.ui.custom.MainTabPage



/**
 * Adapter for the main tab screen
 */
class MainTabLayoutAdapter(private val mContext: Context) : PagerAdapter() {
    private val mTitles = intArrayOf(R.string.tab_feature, R.string.tab_style_notes)

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val mainTabPage = MainTabPage(mContext, position)
        collection.addView(mainTabPage)
        return mainTabPage
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) =
        collection.removeView(view as View)

    override fun getPageTitle(position: Int): CharSequence =
        // Generate title based on item position
        mContext.getString(mTitles[position])

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getCount() = mTitles.size
}

