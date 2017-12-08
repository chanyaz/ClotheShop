package com.valgood.clotheshop.ui.custom

/**
 * Custom recycler view displayed in the main section
 */

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

import com.valgood.clotheshop.R
import com.valgood.clotheshop.adapter.FeatureRecyclerViewAdapter
import com.valgood.clotheshop.backendless.data.DataManager
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.backendless.model.FeatureCategoryResponse
import com.valgood.clotheshop.backendless.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.ArrayList
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController

/**
 * Custom View used for the ViewPager's Pages in MainActivity (Feature, Style Notes)
 */
class MainTabPage : RecyclerView {

    private var mPosition: Int = 0

    /**
     * Constructor receiving a copy of the context and a view
     * @param context
     */
    constructor(context: Context, position: Int) : super(context) {
        mPosition = position
        init()
    }

    /**
     * Constructor receiving a copy of the context and attributes
     * @param context
     * *
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet, position: Int) : super(context, attrs) {
        mPosition = position
        init()
    }

    fun init() {
        val layoutManager = LinearLayoutManager(context)
        setLayoutManager(layoutManager)
        setHasFixedSize(true)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        clipToPadding = false
        //adding some enter animation
        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(context, resId)
        layoutAnimation = animation

        val padingBottom = (resources.getDimension(R.dimen.card_margin) / resources.displayMetrics.density).toInt()

        setPadding(0, 0, 0, padingBottom)

        when (mPosition) {
            0 -> {
                getFeatureCategory()
            }

            1 -> {
            }

            else -> {
            }
        }// Do nothing

    }

    /**
     * Get all the users information from the backend
     */
    private fun getFeatureCategory() {
        DataManager.featuresCategory
                .doOnSubscribe({ /** show spinner or loading here */ })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> processResponse(data) }
    }

    /**
     * Fill in the adapter with the information obtained from the backend
     * *
     * @param data
     */
    private fun processResponse(data: FeatureCategoryResponse) {
        adapter = when(data.code) {
            Constants.Companion.SUCCESS_CODE -> {
                FeatureRecyclerViewAdapter(data.features)
            } else -> {
                FeatureRecyclerViewAdapter(ArrayList())
            }
        }
    }
}

