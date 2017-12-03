package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valgood.clotheshop.R
import kotlinx.android.synthetic.main.product_header_collapsing.view.*

/**
 * Custom view to display the Product Title and Description
 */
class CoordinatorProductDetails : CoordinatorLayout {

    var imageURl: String = ""

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, imageUrl: String) : super(context) {
        this.imageURl = imageURl
        init()
    }
    /**
     * Constructor receiving a copy of the context and attributes
     * @param context
     * *
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init(view: View = LayoutInflater.from(context).inflate(R.layout.product_header_collapsing, this, true)) {
        view.fitsSystemWindows = true
        loadMainImage()
    }

    private fun loadMainImage() {
        Glide.with(context)
                .load(imageURl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(backdrop)
    }

}