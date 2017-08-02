package com.valgood.clotheshop.utils

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import java.util.Hashtable


/**
 * Utility class to cache custom fonts in order to avoid memory leaks
 */
object FontCache {

    private val TAG = FontCache::class.java.name

    private val fontCache = Hashtable<String, Typeface>()

    operator fun get(name: String, context: Context): Typeface? {
        var tf: Typeface? = fontCache[name]
        if (tf == null) {
            Log.d(TAG, "Typeface $name IS NULL")
            try {
                tf = Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                Log.d(TAG, "Typeface $name Exception: $e")
                return null
            }

            fontCache.put(name, tf)
            Log.d(TAG, "Typeface $name in cache")
        }

        return tf
    }
}
