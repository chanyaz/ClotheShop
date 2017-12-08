package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.ProductDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.product_description_custom_text.view.*
import kotlinx.android.synthetic.main.product_detail_expandable_description.view.*
import net.cachapa.expandablelayout.ExpandableLayout

/**
 * Custom view to show the product description
 */
class ProductDescriptionFullView : LinearLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, objectId: String) : super(context) {
        init(objectId)
    }

    private fun init(objectId: String) {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.product_detail_expandable_description,
                        this, true)
        appendDetailsView(objectId, detailsContainer)
        view.descriptionContainer.setOnClickListener {
            if (expandable_description.isExpanded) {
                expandable_description.collapse()
                detail_box_description_arrow.rotation = 90.0f
            } else {
                expandable_description.expand()
                detail_box_description_arrow.rotation = 260.0f
            }
        }
        orientation = VERTICAL
    }

    /**
     * Fill in the description section with the information obtained from the backend
     * *
     * @param data
     */
    private fun appendDetailsView(objectId: String, detailsContainer: LinearLayout) {
        DataManager.getObservableProductDetails(objectId)
                .doOnSubscribe({ /** show spinner or loading here */ })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> processResponse(data, detailsContainer) }
    }

    private fun processResponse(data: List<ProductDetails>, detailsContainer: LinearLayout) {
        data.sortedBy { it.order }.forEach {
            val container = LayoutInflater.from(context)
                    .inflate(R.layout.product_description_custom_text,
                            this, false)

            container.detail_key.text = it.key
            container.detail_value.text = it.value
            detailsContainer.addView(container)
        }
    }
}
