package com.valgood.clotheshop.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valgood.clotheshop.ProductDetailActivity
import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Product
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.text.NumberFormat
import java.util.*


/**
 * Adapter to load the information regarding each product
 */
class ProductHorizontalGridViewAdapter(val data: ArrayList<Product>) :
        RecyclerView.Adapter<ProductHorizontalGridViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                                   .inflate(R.layout.product_recommended_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = data[position]
        holder.apply {
            productName.text = product.description
            productPrice.text = getFormattedPrice(product.price, product.currency)
        }

        Glide.with(holder.itemView.context)
             .load(product.galleryOne)
             .bitmapTransform(RoundedCornersTransformation(holder.itemView.context, 3, 3))
             .override(300,300)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .into(holder.imageInListView)

        holder.itemView.setOnClickListener({
            ProductDetailActivity.Companion.navigate(holder.itemView.context as AppCompatActivity,
                                                     holder.imageInListView, data)
        })
    }

    override fun getItemCount() = data.size

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
//        var row : View? = convertView
//        val listViewHolder: ViewHolder
//
//        when(row) {
//            null -> {
//                row = LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
//                listViewHolder = ViewHolder(row)
//                row.tag = listViewHolder
//            } else -> listViewHolder = row.tag as ViewHolder
//        }
//
//        val product = data[position]
//        listViewHolder.apply {
//            productName.text = product.name
//            productDescription.text = product.description
//            productPrice.text = getFormattedPrice(product.price, product.currency)
//        }
//
//        when {
//            product.discount > 0  -> listViewHolder.productNewDiscount.text =
//                                        context.getString(R.string.product_text_discount,
//                                                          product.discount)
//            !product.new -> listViewHolder.productNewDiscount.visibility = View.INVISIBLE
//        }
//
//
//        val context = listViewHolder.imageInListView.context
//        Glide.with(context)
//             .load(product.galleryOne)
//             .bitmapTransform(RoundedCornersTransformation(context, 3, 3))
//             .override(300,300)
//             .diskCacheStrategy(DiskCacheStrategy.RESULT)
//             .into(listViewHolder.imageInListView)
//
//        row?.setOnClickListener({
//            ProductDetailActivity.Companion.navigate(parent.context as AppCompatActivity,
//                                                     listViewHolder.imageInListView, data)
//        })
//        return row
//    }

    private fun  getFormattedPrice(price: Double, currency: String): CharSequence? {
        // Use currencyFormatter to place currency symbol correctly depending on the locale
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        // Set the currency using the product's currency symbol
        currencyFormatter.currency = Currency.getInstance(currency)
        return currencyFormatter.format(price)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var imageInListView: ImageView = row.findViewById(R.id.single_product_image) as ImageView
        var productName: TextView = row.findViewById(R.id.single_product_title) as TextView
        var productPrice: TextView = row.findViewById(R.id.single_product_price) as TextView
    }

}