package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.valgood.clotheshop.R
import com.valgood.clotheshop.adapter.ProductHorizontalGridViewAdapter
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.Product
import com.valgood.clotheshop.backendless.model.ProductResponse
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.product_recomended_block.view.*
import java.util.*

/**
 * Custom view to display single product recommendation block
 */
class ProductRecommendView : LinearLayout {
    init {
        orientation = VERTICAL
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.product_recomended_block,
                        this, true)
        //view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        view.block_title.text = "You may also like"
        view.block_display_more.text = "See all"
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
        block_horizontal_view.adapter = when(data.code) {
            Constants.Companion.SUCCESS_CODE -> {
                ProductHorizontalGridViewAdapter(data.products as ArrayList<Product>)
            } else -> {
                ProductHorizontalGridViewAdapter(ArrayList())
            }
        }
        block_horizontal_view.adapter.notifyDataSetChanged()
    }
}