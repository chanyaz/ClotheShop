package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.v17.leanback.widget.HorizontalGridView
import android.widget.GridLayout
import android.widget.GridView
import com.valgood.clotheshop.R
import com.valgood.clotheshop.adapter.ProductHorizontalGridViewAdapter
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.Product
import com.valgood.clotheshop.backendless.model.ProductResponse
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*

/**
 * Specific Implementation for the Product Grid View
 */
class ProductHorizontalGridView(context: Context) : HorizontalGridView(context) {
    init {
        setNumRows(1)
        clipToPadding = false
        isVerticalScrollBarEnabled = false
        isFocusable = true
        isClickable = true
        val spacingMargins =
                getContext().resources.getDimensionPixelSize(R.dimen.product_spacing_and_margins)
        val layoutParams = GridLayout.LayoutParams()
        layoutParams.setMargins(spacingMargins, spacingMargins, spacingMargins, spacingMargins)
        layoutParams.setGravity(TEXT_ALIGNMENT_CENTER)
        setLayoutParams(layoutParams)
        setRowHeight(getContext().resources.getDimensionPixelSize(R.dimen.product_column_height))
        //verticalSpacing = spacingMargins
        //horizontalSpacing = spacingMargins
        //horizontalSpacing = spacingMargins
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
                ProductHorizontalGridViewAdapter(data.products as ArrayList<Product>)
            } else -> {
                ProductHorizontalGridViewAdapter(ArrayList())
            }
        }
    }
}