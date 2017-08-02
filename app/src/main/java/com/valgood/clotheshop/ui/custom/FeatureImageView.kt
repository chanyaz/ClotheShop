package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.utils.ImageLoading
import kotlinx.android.synthetic.main.feature_item.view.*

/**
 * Customs view with the information displayed in the feature section.
 */

class FeatureImageView : LinearLayout {
    constructor(context: Context) : super(context) {
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

    private fun init(view: View = LayoutInflater.from(context).inflate(R.layout.feature_item, this, true)) {
        orientation = LinearLayout.VERTICAL
    }

    fun setData(data: Feature) {
        //this.data = data;
        main_txt.text = data.title
        main_sub_txt.text = data.description
        ImageLoading.loadPicture(data.picture, main_pic)
    }
}
