package com.valgood.clotheshop

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.valgood.clotheshop.backendless.model.Product
import android.view.*
import com.valgood.clotheshop.adapter.ProductDetailParallaxAdapter
import com.valgood.clotheshop.utils.openActivity
import kotlinx.android.synthetic.main.product_detail_paralax_view_pager.*


/**
 * Activity to display the product details
 */
class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initActivityTransitions()
        setContentView(R.layout.product_detail_paralax_view_pager)

        val products: List<Product> =
                intent.extras.getParcelableArrayList(EXTRA_PRODUCTS)
        viewpager.adapter = ProductDetailParallaxAdapter(products)
    }


//    private fun initActivityTransitions() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val transition = Slide()
//            transition.excludeTarget(android.R.id.statusBarBackground, true)
//            window.enterTransition = transition
//            window.returnTransition = transition
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // click on 'up' button in the action bar, handle it here
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {

        //private val EXTRA_TITLE = "com.valgood.clotheshop.extraTitle"
        private val EXTRA_IMAGE = "com.valgood.clotheshop.extraImage"
        private val EXTRA_PRODUCTS = "com.valgood.clotheshop.extraProduct"


        fun navigate(activity: AppCompatActivity, transitionImage: View, viewModel: ArrayList<Product>) {
            //val intent = Intent(activity, ProductDetailActivity::class.java)
            //intent.putExtra(EXTRA_PRODUCTS, viewModel)
            //intent.putExtra(EXTRA_IMAGE, viewModel.galleryOne)

            //val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE)
            //ActivityCompat.startActivity(activity, intent, options.toBundle())
            val bundle = Bundle().apply {
                putParcelableArrayList(EXTRA_PRODUCTS, viewModel)
            }
            openActivity(activity, ProductDetailActivity::class, bundle).invoke()
        }
    }
}