package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Product
import kotlinx.android.synthetic.main.product_detail_title.view.*
import java.util.*

/**
 * Custom view to display the Product Title and Description
 */
class ProductTitleDescriptionView : RelativeLayout {

    constructor(context: Context) : super(context) {
        init(Product())
    }

    constructor(context: Context, product: Product ) : super(context) {
        init(product)
    }

    private fun init(product: Product) {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.product_detail_title,
                        this, true)
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        view.title.text = product.name
        view.description.text = product.description
        view.price.text = currencyFormatter(product.price, product.currency)

    }

    private fun currencyFormatter(price: Double, currencyCode: String): String {
        // Use currencyFormatter to place currency symbol correctly depending on the locale
        val currencyFormatter = java.text.NumberFormat.getCurrencyInstance(Locale.getDefault())

        // Set the currency using the user's ChargeNow market
        currencyFormatter.currency = Currency.getInstance(currencyCode)
        return currencyFormatter.format(price)
    }
}