package com.valgood.clotheshop.backendless.model

/**
 * Response object to store the status and information returned by backendless
 */
data class ProductResponse(var code: String,
                                   var message: String,
                                   var products: List<Product>) : OperationResponse(code, message)