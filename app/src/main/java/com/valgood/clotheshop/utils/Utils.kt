package com.valgood.clotheshop.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlin.reflect.KClass

/**
 * General purpose methods
 */
object Utils {
    var PRODUCT_OBJECT_ID_KEY = "PRODUCT_OBJECT_ID"
    var PRODUCT_NAME_KEY = "PRODUCT_NAME"
    var PRODUCT_DESCRIPTION_KEY = "PRODUCT_DESCRIPTION"

    fun <T : Activity> openActivity(context: Context, activity: KClass<T>, bundle: Bundle): (View) -> Boolean = {
        val intent = Intent(context, activity.java)
        intent.putExtras(bundle)
        context.startActivity(intent)
        false
    }
}