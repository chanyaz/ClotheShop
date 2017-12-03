package com.valgood.clotheshop.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlin.reflect.KClass

const val PRODUCT_OBJECT_ID_KEY = "PRODUCT_OBJECT_ID"
const val PRODUCT_NAME_KEY = "PRODUCT_NAME"
const val PRODUCT_DESCRIPTION_KEY = "PRODUCT_DESCRIPTION"

/**
 * General purpose methods
 */
fun <T : Activity> openActivity(context: Context, activity: KClass<T>, bundle: Bundle) = {
    val intent = Intent(context, activity.java).apply {
        putExtras(bundle)
    }
    context.startActivity(intent)
}