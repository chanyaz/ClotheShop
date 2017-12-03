package com.valgood.clotheshop.adapter

/**
 * Adapter to handle the feature information items
 */

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.valgood.clotheshop.ProductByFeatureActivity

import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.ui.custom.FeatureImageView
import com.valgood.clotheshop.utils.PRODUCT_DESCRIPTION_KEY
import com.valgood.clotheshop.utils.PRODUCT_NAME_KEY
import com.valgood.clotheshop.utils.PRODUCT_OBJECT_ID_KEY
import com.valgood.clotheshop.utils.openActivity

import java.util.ArrayList

/**
 * Adapter containing the elements shown inside the recycler view under every tab.

 */
class FeatureRecyclerViewAdapter(contents: List<Feature>) :
        RecyclerView.Adapter<SectionViewHolder>() {

    private val mContents: MutableList<Feature> = ArrayList(contents)

    override fun getItemCount() = mContents.size

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.feature_content, parent, false) as CardView

        view.addView(FeatureImageView(parent.context))
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.imageSectionView.apply {
            setData(mContents[position])
            setOnClickListener({
                val bundle = Bundle().apply {
                    putString(PRODUCT_OBJECT_ID_KEY, mContents[position].objectId)
                    putString(PRODUCT_NAME_KEY, mContents[position].title)
                    putString(PRODUCT_DESCRIPTION_KEY, mContents[position].description)
                }
                openActivity(it.context, ProductByFeatureActivity::class, bundle).invoke()
            })
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        mContents.clear()
    }
}

/**
 * View Holder class with only one instance of ImageSectionView per item
 */
class SectionViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView) {
    val imageSectionView = itemView.getChildAt(0) as FeatureImageView
}

