package com.valgood.clotheshop.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Utils to load images with glide
 */
object ImageLoading {

    private val TAG = "ImageLoading"
    /**
     * Load a URL image with glide
     * @param profileURL
     * *
     * @param imageView
     */
    fun loadPicture(profileURL: String, imageView: ImageView) {
        Glide.with(imageView.context).load(profileURL)
                //.bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView)
    }

    //    public static Bitmap getPictureForNotification(Context context, String pictureURL) {
    //        Bitmap roundedBitmap = null;
    //        try {
    //            Bitmap srcBitmap = Glide.with(context.getApplicationContext()) // safer!
    //                    .load(pictureURL)
    //                    .asBitmap().into(100,100).get();
    //
    //            roundedBitmap = ImageLoading.getCircleBitmap(srcBitmap);
    //        } catch (InterruptedException | ExecutionException e) {
    //            Log.e(TAG, "Error getting profile pic with glide");
    //        }
    //
    //        if (roundedBitmap == null) {
    //            roundedBitmap = BitmapFactory.
    //                    decodeResource(context.getResources(), R.drawable.ic_launcher);
    //        }
    //        return roundedBitmap;
    //    }
    /**
     * Circle the notification bitmap
     * @param bitmap
     * *
     * @return
     */
    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width,
                bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val color = Color.RED
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawOval(rectF, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        //If the bitmap gets recycled an exception occurs every time a new offline message comes in
        //bitmap.recycle();

        return output
    }
}
