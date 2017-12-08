package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.AbsListView
import android.widget.GridLayout
import android.widget.GridView
import com.valgood.clotheshop.R
import com.valgood.clotheshop.adapter.ProductDetailPicsGridViewAdapter
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.ProductResponse
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.ArrayList

/**
 * Specific Implementation for the Product Detail Pictures Grid View
 */
class ProductDetailPicsGridView(context: Context) : GridView(context) {
    init {
        val resourcesNumColumns = context.resources
                .getInteger(R.integer.product_detail_grid_view_num_columns)
        numColumns = if (resourcesNumColumns == 0) {
            AUTO_FIT
        } else {
            resourcesNumColumns
        }
        clipToPadding = false
        isVerticalScrollBarEnabled = false
        isFocusable = true
        transcriptMode = AbsListView.TRANSCRIPT_MODE_DISABLED
        isClickable = true
        val spacingMargins = getContext().resources.getDimensionPixelSize(R.dimen.product_detail_pictures_margin_left)
        val layoutParams = GridLayout.LayoutParams().apply {
            height = getContext().resources.getDimensionPixelSize(R.dimen.product_detail_grid_view_height)
            setMargins(spacingMargins, spacingMargins, spacingMargins, spacingMargins)
            setGravity(TEXT_ALIGNMENT_CENTER)
        }
        setLayoutParams(layoutParams)
        columnWidth = getContext().resources.getDimensionPixelSize(R.dimen.product_detail_grid_view_width)
        setDrawSelectorOnTop(true)
        stretchMode = STRETCH_COLUMN_WIDTH
        val spacingItemMargins = getContext().resources.getDimensionPixelSize(R.dimen.product_detail_pictures_spacing_and_margins)
        verticalSpacing = spacingItemMargins
        horizontalSpacing = spacingItemMargins

        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
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
        when (data.code) {
            Constants.Companion.SUCCESS_CODE -> {
                val productDetailsPictures = ArrayList<String>()
                data.products
                        .forEach({product -> productDetailsPictures.add(product.galleryOne) })
                        .also { adapter = ProductDetailPicsGridViewAdapter(context, productDetailsPictures) }
            } else -> {
                /**show toast**/
               adapter = ProductDetailPicsGridViewAdapter(context, ArrayList())
            }
        }
    }
}