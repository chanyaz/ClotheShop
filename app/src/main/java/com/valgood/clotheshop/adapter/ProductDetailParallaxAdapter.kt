package com.valgood.clotheshop.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Product
import com.valgood.clotheshop.ui.custom.ProductDescriptionFullView
import com.valgood.clotheshop.ui.custom.ProductDetailPicsGridView
import com.valgood.clotheshop.ui.custom.ProductTitleDescriptionView
import kotlinx.android.synthetic.main.product_detail_main_2.view.*
import kotlinx.android.synthetic.main.toolbar_product_detail.view.*
import kotlinx.android.synthetic.main.toolbar_product_detail_transparent.view.*
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter


/**
 * Main Adapter for Product Detail Parallax Effect
 */
class ProductDetailParallaxAdapter constructor(private var products: List<Product>): PagerAdapter() {

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean = arg0 === arg1

    override fun destroyItem(container: ViewGroup, position: Int,
                             obj: Any) {
        container.removeView(obj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(container.context, R.layout.product_detail_main_2, null).apply {
            coordinatorContainer.isNestedScrollingEnabled = true
        }
        val currentProduct = products[position]
        loadMainImage(view, currentProduct.galleryOne)
        loadTitleAndDescription(view, currentProduct)
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        return view
    }

    private fun loadTitleAndDescription(view: View, product: Product) {
        // Create a new merge adapter.

        view.toolbarProductDetailFixed.rootView.apply {
            title.text = product.name
            description.text = product.description
        }
        val mergeAdapter = RecyclerViewMergeAdapter().apply {
            addView(ProductTitleDescriptionView(view.context, product))
            addView(ProductDetailPicsGridView(view.context))
            addView(ProductDescriptionFullView(view.context, product.details))
        }
        view.product_details_recycler_view.apply {
            adapter = mergeAdapter
            layoutManager = LinearLayoutManager(view.context)
            isNestedScrollingEnabled = true
        }

        configureAlphaEffect(view)
    }

    private fun configureAlphaEffect(view: View) {
        val toolbarMinimumHeight =
                ViewCompat.getMinimumHeight(view.toolbarProductTransDetail) -
                        view.context.resources.
                                getDimension(R.dimen.product_item_recycler_view_margin_top) /3.5f
        view.app_bar_layout.addOnOffsetChangedListener { _ , verticalOffset ->
            val alphaOffset = verticalOffset.toFloat() /
                    (view.collapsing_toolbar.height - toolbarMinimumHeight)
            view.collapsing_toolbar.alpha = alphaOffset + 1.0f

            if(view.collapsing_toolbar.height.plus(verticalOffset) <= toolbarMinimumHeight) {
                view.fab.hide()
                view.toolbarProductDetailFixed.visibility = View.VISIBLE
            } else {
                view.fab.show()
                view.toolbarProductDetailFixed.visibility = View.INVISIBLE
            }
        }
    }

    private fun loadMainImage(view: View, imagePath: String) {
        Glide.with(view.context)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view.imageDetail)
    }

    override fun getCount(): Int = products.size
}