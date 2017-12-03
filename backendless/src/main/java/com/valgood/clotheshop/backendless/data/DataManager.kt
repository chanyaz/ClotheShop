package com.valgood.clotheshop.backendless.data

import android.util.Log
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.valgood.clotheshop.backendless.model.*
import com.valgood.clotheshop.backendless.utils.Constants

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import com.backendless.persistence.LoadRelationsQueryBuilder
import com.backendless.persistence.QueryOptions
import com.backendless.persistence.BackendlessDataQuery





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
//            val query = BackendlessDataQuery()
//            val queryOptions = QueryOptions()
//            queryOptions.addRelated("details")
//            queryOptions.addSortByOption("Order")
//            query.queryOptions = queryOptions

            val sortBy = "Order "
            val queryBuilder = DataQueryBuilder.create()
            queryBuilder.setSortBy(sortBy)
            Backendless.Data.of(Product::class.java).find(queryBuilder,
                    object : AsyncCallback<List<Product>> {
                override fun handleResponse(productCollection: List<Product>) {
                        var counter = 0
                        val loadRelationsQueryBuilder =
                                LoadRelationsQueryBuilder.of<ProductDetails>(ProductDetails::class.java)
                        loadRelationsQueryBuilder.setRelationName("details")
                        loadRelationsQueryBuilder.setPageSize(15)
                        productCollection.forEach {
                                Backendless.Data.of(Product::class.java)
                                        .loadRelations(it.objectId, loadRelationsQueryBuilder,
                                                object : AsyncCallback<List<ProductDetails>> {
                                                    override fun handleResponse(response: List<ProductDetails>) {
                                                        it.details = response
                                                        counter++
                                                        if (counter >= productCollection.size) {
                                                            val responseFinal =
                                                                    ProductResponse(Constants.SUCCESS_CODE,
                                                                            "",
                                                                            productCollection)
                                                            subscriber.onNext(responseFinal)
                                                        }
                                                    }

                                                    override fun handleFault(fault: BackendlessFault) {
                                                        counter++
                                                        if (counter >= productCollection.size) {
                                                            val response =
                                                                    ProductResponse(Constants.SUCCESS_CODE,
                                                                            "",
                                                                            productCollection)
                                                            subscriber.onNext(response)
                                                        }
                                                    }
                                                })
                                }
                }

                override fun handleFault(backendlessFault: BackendlessFault) {
                    val response = ProductResponse(backendlessFault.code, backendlessFault.message, ArrayList())
                    subscriber.onNext(response)
                }
            })
        }).subscribeOn(Schedulers.io())

    /**
     * Obtain the Product Details associated to a specific Product
     */
    fun getObservableProductDetails(productId: String) : Observable<List<ProductDetails>>  =
            Observable.create(ObservableOnSubscribe<List<ProductDetails>> { subscriber ->
                val loadRelationsQueryBuilder =
                        LoadRelationsQueryBuilder.of<ProductDetails>(ProductDetails::class.java)
                loadRelationsQueryBuilder.setRelationName("details")
                Backendless.Data.of( Product::class.java ).loadRelations(
                        productId,
                        loadRelationsQueryBuilder,
                        object: AsyncCallback<List<ProductDetails>> {
                            override fun handleResponse(response: List<ProductDetails>) {
                                subscriber.onNext(response)
                            }

                            override fun handleFault(fault: BackendlessFault) {
                                Log.e("DataManager", "Error: " + fault.message)
                                subscriber.onNext(ArrayList())
                            }
                        })
            }).subscribeOn(Schedulers.io())
}
