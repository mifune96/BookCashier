package com.tomuchcoffee.bookchasir.source.network

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.map
import com.tomuchcoffee.bookchasir.source.local.ProductDao
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.source.model.transaction.DetailTransactionResponse
import com.tomuchcoffee.bookchasir.source.model.transaction.TransactionResponse
import org.koin.dsl.module

val repositoryModule = module {
    factory { BookChasirRepository(get(), get()) }
}

class BookChasirRepository(
    private val api: ApiClient,
    val db: ProductDao

) {

    suspend fun Sigin(
        auth: AuthRequest
    ): AuthResponse {
        return api.auth(auth)
    }

    suspend fun GetAllProduct(
    ): ProductResponse {
        return api.getProductAll()
    }

    suspend fun postCheckout(
        checkOutResponse: CheckOutResponse
    ): CheckOutResponse {
        return api.checkout(checkOutResponse)
    }

    suspend fun getAllTransaction(
        keyword: String
    ): TransactionResponse {
        return api.getAllTransaction(keyword)
    }

    suspend fun getDetailTransaction(
        id: Int
    ): DetailTransactionResponse {
        return api.getDetailTransaction(id)
    }


    suspend fun find(productModel: Products) = db.find(productModel.published)

    suspend fun remove(productModel: Products) {
        db.remove(productModel)
    }

    suspend fun update(productModel: Products) {
        db.update(productModel)
    }

    suspend fun deleteAllDao() {
        db.deletAllProduck()
    }

    suspend fun incrementQty(productModel: Products) {
        val producNow = productModel.id?.let { db.getOneProduct(it) }
        Log.d(TAG, "incrementQty: " + producNow)
        Log.d(TAG, "produk id: " + productModel)
        if (producNow != null) {
            producNow.productbuyqty += 1
//            producNow.productbuyqty++
            db.update(producNow)
        } else {
            productModel.productbuyqty = 1
            db.save(productModel)
        }


    }


}