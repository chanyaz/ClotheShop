package com.valgood.clotheshop.backendless.data

import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.valgood.clotheshop.backendless.model.Feature
import com.valgood.clotheshop.backendless.model.FeatureCategoryResponse
import com.valgood.clotheshop.backendless.model.Product
import com.valgood.clotheshop.backendless.model.ProductResponse
import com.valgood.clotheshop.backendless.utils.Constants

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

/**
 * Class to handle all the operations performed with Backendless
 */
object DataManager {
    /**
     * Observable to obtain all the feature items registered in backendless
     * @return
     */
    val featuresCategory: Observable<FeatureCategoryResponse>
        get() = Observable.create(ObservableOnSubscribe<FeatureCategoryResponse> { subscriber ->
            val sortBy = "Order "
            val queryBuilder = DataQueryBuilder.create()
            queryBuilder.setSortBy(sortBy)
            Backendless.Data.of(Feature::class.java).find(queryBuilder, object : AsyncCallback<List<Feature>> {
                override fun handleResponse(featureCollection: List<Feature>) {
                    val response = FeatureCategoryResponse(Constants.SUCCESS_CODE, "", featureCollection)
                    subscriber.onNext(response)
                }

                override fun handleFault(backendlessFault: BackendlessFault) {
                    val response = FeatureCategoryResponse(backendlessFault.code, backendlessFault.message, ArrayList<Feature>())
                    subscriber.onNext(response)
                }
            })
        }).subscribeOn(Schedulers.io())

    val products: Observable<ProductResponse>
        get() = Observable.create(ObservableOnSubscribe<ProductResponse> { subscriber ->
            val sortBy = "Order "
            val queryBuilder = DataQueryBuilder.create()
            queryBuilder.setSortBy(sortBy)
            Backendless.Data.of(Product::class.java).find(queryBuilder, object : AsyncCallback<List<Product>> {
                override fun handleResponse(productCollection: List<Product>) {
                    val response = ProductResponse(Constants.SUCCESS_CODE, "", productCollection)
                    subscriber.onNext(response)
                }

                override fun handleFault(backendlessFault: BackendlessFault) {
                    val response = ProductResponse(backendlessFault.code, backendlessFault.message, ArrayList<Product>())
                    subscriber.onNext(response)
                }
            })
        }).subscribeOn(Schedulers.io())
}
