package com.valgood.clotheshop.backendless.model

/**
 * Response object to store the status and information returned by backendless
 */
data class FeatureCategoryResponse(var code: String,
                                   var message: String,
                                   var features: List<Feature>) : OperationResponse(code, message)