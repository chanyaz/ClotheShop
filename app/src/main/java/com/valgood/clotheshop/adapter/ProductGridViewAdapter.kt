package com.valgood.clotheshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valgood.clotheshop.R
import com.valgood.clotheshop.backendless.model.Product
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

/**
 * Adapter to load the information regarding each product
 */
class ProductGridViewAdapter (context: Context,
                              val data: MutableList<Product>) :
        ArrayAdapter<Product>(context, R.layout.product_item_view, data) {

    /**
     * Updates grid data and refresh grid items.
     * @param gridData
     */
    fun setGridData(gridData: List<Product>) {
        data.addAll(gridData)
        notifyDataSetChanged()
    }

    fun clearGridData() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var row : View? = convertView
        val listViewHolder: ViewHolder

        when(row) {
            null -> {
                row = LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
                listViewHolder = ViewHolder(row)
                row.tag = listViewHolder
            } else -> listViewHolder = row.tag as ViewHolder
        }

        val product = data[position]
        listViewHolder.productName.text = product.name
        listViewHolder.productDescription.text = product.description
        listViewHolder.productPrice.text = getFormattedPrice(product.price, product.currency)

        if (product.discount > 0) {
            listViewHolder.productNewDiscount.text = context.getString(R.string.product_text_discount, product.discount)
        } else if (!product.new) {
            listViewHolder.productNewDiscount.visibility = View.INVISIBLE
        }

        val context = listViewHolder.imageInListView.context
        val transformation = RoundedCornersTransformation(context, 3, 3)
        Glide.with(context)
             .load(product.picture)
             .bitmapTransform(transformation)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .into(listViewHolder.imageInListView)

        return row
    }

    private fun  getFormattedPrice(price: Double, currency: String): CharSequence? {
        // Use currencyFormatter to place currency symbol correctly depending on the locale
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        // Set the currency using the product's currency symbol
        currencyFormatter.currency = Currency.getInstance(currency)
        return currencyFormatter.format(price)
    }

    override fun getItem(position: Int): Product = data[position]
    override fun getCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()

    internal class ViewHolder(row: View?) {
        var imageInListView: ImageView = row?.findViewById<ImageView>(R.id.productView) as ImageView
        var productName: TextView = row?.findViewById<TextView>(R.id.productName) as TextView
        var productDescription: TextView = row?.findViewById<TextView>(R.id.productDescription) as TextView
        var productPrice: TextView = row?.findViewById<TextView>(R.id.productPrice) as TextView
        var productNewDiscount: TextView = row?.findViewById<TextView>(R.id.productNewDiscount) as TextView
    }
}