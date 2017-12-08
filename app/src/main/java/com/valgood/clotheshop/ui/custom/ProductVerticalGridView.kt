package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.AbsListView
import android.widget.GridLayout
import android.widget.GridView
import com.valgood.clotheshop.R
import com.valgood.clotheshop.adapter.FeatureRecyclerViewAdapter
import com.valgood.clotheshop.adapter.ProductGridViewAdapter
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.*
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import java.util.ArrayList

/**
 * Specific Implementation for the Product Grid View
 */
class ProductVerticalGridView(context: Context) : GridView(context) {
    init {
        numColumns = GridView.AUTO_FIT
        clipToPadding = false
        isVerticalScrollBarEnabled = false
        isFocusable = true
        transcriptMode = AbsListView.TRANSCRIPT_MODE_DISABLED
        isClickable = true
        val spacingMargins = getContext().resources.getDimensionPixelSize(R.dimen.product_spacing_and_margins)
        val layoutParams = GridLayout.LayoutParams()
        layoutParams.setMargins(spacingMargins, spacingMargins, spacingMargins, spacingMargins)
        layoutParams.setGravity(TEXT_ALIGNMENT_CENTER)
        setLayoutParams(layoutParams)
        columnWidth = getContext().resources.getDimensionPixelSize(R.dimen.product_column_width)
        setDrawSelectorOnTop(true)
        stretchMode = STRETCH_COLUMN_WIDTH
        verticalSpacing = spacingMargins
        horizontalSpacing = spacingMargins

        getProductsByCategory()
    }

    /**
     * Get all the users information from the backend
     */
    private fun getProductsByCategory() {
        DataManager.products
                .doOnSubscribe({ /** show spinner or loading here */ })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> processResponse(data) }
    }

    /**
     * Fill in the adapter with the information obtained from the backend
     * *
     * @param data
     */
    private fun processResponse(data: ProductResponse) {
        adapter = when(data.code) {
            Constants.Companion.SUCCESS_CODE -> {
                ProductGridViewAdapter(context, data.products as ArrayList<Product>)
            } else -> {
                ProductGridViewAdapter(context, ArrayList())
            }
        }
    }
}