package com.valgood.clotheshop.ui.custom

import android.content.Context
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View


/**
 * Created by valgood on 8/31/2017.
 */
class OverScrollBounceBehavior : CoordinatorLayout.Behavior<View> {

    private var mOverScrollY: Float = 0.0f

    constructor() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        mOverScrollY = 0.0f
        return true
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        if (dyUnconsumed == 0) {
            return
        }

        mOverScrollY -= dyUnconsumed
        val group = target as ViewGroup
        val count = group.childCount
        (0 until count)
                .map { group.getChildAt(it) }
                .forEach { it.translationY = mOverScrollY }
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View) {
        val group = target as ViewGroup
        val count = group.childCount
        (0 until count)
                .map { group.getChildAt(it) }
                .forEach { ViewCompat.animate(it).translationY(0f).start() }
    }
}