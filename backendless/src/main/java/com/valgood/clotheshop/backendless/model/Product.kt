package com.valgood.clotheshop.backendless.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Product matching the names of the table in backendless
 */
class Product() : Parcelable {
    lateinit var objectId: String
    lateinit var name: String
    lateinit var description: String
    lateinit var picture: String
    lateinit var currency: String
    lateinit var galleryOne: String
    var details: List<ProductDetails>? = null
    var discount = 0
    var new: Boolean = false
    var price: Double = 0.0

    constructor(parcel: Parcel) : this() {
        objectId = parcel.readString()
        name = parcel.readString()
        description = parcel.readString()
        picture = parcel.readString()
        currency = parcel.readString()
        galleryOne = parcel.readString()
        details = parcel.createTypedArrayList(ProductDetails)
        discount = parcel.readInt()
        new = parcel.readByte() != 0.toByte()
        price = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(objectId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(picture)
        parcel.writeString(currency)
        parcel.writeString(galleryOne)
        parcel.writeTypedList(details)
        parcel.writeInt(discount)
        parcel.writeByte(if (new) 1 else 0)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}