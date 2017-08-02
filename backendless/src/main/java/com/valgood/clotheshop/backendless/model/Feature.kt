package com.valgood.clotheshop.backendless.model

import java.io.Serializable

/**
 * Feature category matching the names of the table in backendless
 */
class Feature : Serializable {
    lateinit  var objectId: String
    lateinit var title: String
    lateinit var description: String
    lateinit var picture: String
    var items: Product = Product()
}
