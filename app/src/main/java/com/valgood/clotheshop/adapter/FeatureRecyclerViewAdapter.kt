package com.valgood.clotheshop.adapter

/**
 * Adapter to handle the feature information items
 */

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valgood.clotheshop.ProductByFeatureActivity

import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.ui.custom.FeatureImageView
import com.valgood.clotheshop.utils.Utils

import java.util.ArrayList
import kotlin.reflect.KClass

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
        holder.imageSectionView.setData(mContents[position])
        holder.imageSectionView.setOnClickListener({
            val featureInfo = mContents[position]
            val intent = Intent(it.context, ProductByFeatureActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Utils.PRODUCT_OBJECT_ID_KEY, featureInfo.objectId)
            bundle.putString(Utils.PRODUCT_NAME_KEY, featureInfo.title)
            bundle.putString(Utils.PRODUCT_DESCRIPTION_KEY, featureInfo.description)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        })
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

