package com.valgood.clotheshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valgood.clotheshop.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Adapter to load the information regarding each product
 */
class ProductDetailPicsGridViewAdapter (context: Context,
                              val data: MutableList<String>) :
        ArrayAdapter<String>(context, R.layout.product_detail_pics_view, data) {

    /**
     * Updates grid data and refresh grid items.
     * @param gridData
     */
//    fun setGridData(gridData: List<String>) {
//        gridData.forEach { data.add(it) }
//        notifyDataSetChanged()
//    }
//
//    fun clearGridData() {
//        data.clear()
//        notifyDataSetChanged()
//    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var row : View? = convertView
        val listViewHolder: ViewHolder

        when(row) {
            null -> {
                row = LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_detail_pics_view, parent, false)
                listViewHolder = ViewHolder(row)
                row.tag = listViewHolder
            } else -> listViewHolder = row.tag as ViewHolder
        }

        val productPic = data[position]

        val transformation = RoundedCornersTransformation(context, 3, 3)
        Glide.with(context)
             .load(productPic)
             .bitmapTransform(transformation)
//             .override(100,300)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .into(listViewHolder.imageInListView)

        row?.setOnClickListener({
//            ProductDetailActivity.Companion.navigate(parent.context as AppCompatActivity,
//                                                     listViewHolder.imageInListView, product)
        })
        return row
    }


    override fun getItem(position: Int) = data[position]
    override fun getCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()

    internal class ViewHolder(row: View?) {
        var imageInListView: ImageView = row?.findViewById(R.id.productPic) as ImageView
    }

}