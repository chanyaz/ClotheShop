package com.valgood.clotheshop.backendless.model

import android.os.Parcel
import android.os.Parcelable


/**
 * Product Details information
 */
class ProductDetails() : Parcelable {
    lateinit var key: String
    lateinit var value: String
    var order = 0

    constructor(parcel: Parcel) : this() {
        key = parcel.readString()
        value = parcel.readString()
        order = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(value)
        parcel.writeInt(order)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDetails> {
        override fun createFromParcel(parcel: Parcel): ProductDetails {
            return ProductDetails(parcel)
        }

        override fun newArray(size: Int): Array<ProductDetails?> {
            return arrayOfNulls(size)
        }
    }


}