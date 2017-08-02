package com.valgood.clotheshop.backendless.model

/**
 * Wrapper for the operation response when an operation to the backend is requested
 */
open class OperationResponse(val opCode: String, val error: String)
