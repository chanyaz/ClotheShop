package com.valgood.clotheshop.backendless.model

import java.io.Serializable

/**
 * Product matching the names of the table in backendless
 */
class Product : Serializable {
    lateinit var name: String
    lateinit var description: String
    lateinit var picture: String
    lateinit var currency: String
    var discount = 0
    var new: Boolean = false
    var price: Double = 0.0
}