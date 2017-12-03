package com.valgood.clotheshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ImageView
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.iconics.IconicsDrawable
import com.valgood.clotheshop.ui.custom.ProductGridView
import com.valgood.clotheshop.utils.PRODUCT_DESCRIPTION_KEY
import com.valgood.clotheshop.utils.PRODUCT_NAME_KEY

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
            action_bar_title_1.text = intent.extras?.let {
                it.getString(PRODUCT_NAME_KEY) ?: ""
            }
            action_bar_title_2.text = intent.extras?.let {
                it.getString(PRODUCT_DESCRIPTION_KEY) ?: ""
            }
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.product_list_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_cart).apply {
            actionView.findViewById<ImageView>(R.id.cart)
                        .setImageBitmap(IconicsDrawable(this@ProductByFeatureActivity)
                        .icon(FontAwesome.Icon.faw_shopping_cart).toBitmap())
        }

        menu.findItem(R.id.action_categories).apply {
            (actionView as ImageView)
                    .setImageBitmap(IconicsDrawable(this@ProductByFeatureActivity)
                    .icon(FontAwesome.Icon.faw_tags).toBitmap())
        }

        return true
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
