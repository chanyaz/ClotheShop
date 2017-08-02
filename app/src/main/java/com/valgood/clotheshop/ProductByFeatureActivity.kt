package com.valgood.clotheshop

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.valgood.clotheshop.ui.custom.ProductGridView
import com.valgood.clotheshop.utils.Utils

import kotlinx.android.synthetic.main.activity_product_by_feature.*
import kotlinx.android.synthetic.main.toolbar_product_list.*

class ProductByFeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_by_feature)
        setupToolbar()
        //setupTabs()
        mainContent.addView(ProductGridView(this))
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarProductList)
        supportActionBar?.apply {
            action_bar_title_1.text = intent.extras?.getString(Utils.PRODUCT_NAME_KEY) ?: ""
            action_bar_title_2.text = intent.extras?.getString(Utils.PRODUCT_DESCRIPTION_KEY) ?: ""
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupTabs() {
        sort_tab.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_long_arrow_tab).
                color(ContextCompat.getColor(this, R.color.colorPrimaryDark)), null)
        filter_tab.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_long_arrow_tab), null)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.product_list_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val itemCart = menu.findItem(R.id.action_cart)
        val cartView = itemCart.actionView as FrameLayout
        cartView.findViewById<ImageView>(R.id.cart)
                .setImageBitmap(IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_shopping_cart).toBitmap())

        val itemCategory = menu.findItem(R.id.action_categories)
        val categoryView = itemCategory.actionView as ImageView
        categoryView.setImageBitmap(IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_tags).toBitmap())

        return true
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
