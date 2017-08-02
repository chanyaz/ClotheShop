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
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.backendless.model.FeatureCategoryResponse
import com.valgood.clotheshop.backendless.model.Product
import com.valgood.clotheshop.backendless.model.ProductResponse
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.ArrayList

/**
 * Specific Implementation for the Product Grid View
 */
class ProductGridView(context: Context) : GridView(context) {
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
        var localAdapter = ProductGridViewAdapter(context, ArrayList<Product>())
        if (data.code == Constants.Companion.SUCCESS_CODE) {
            localAdapter.setGridData(data.products as MutableList<Product>)
        } else {
            //set error in toast
        }

        adapter = localAdapter
    }
}