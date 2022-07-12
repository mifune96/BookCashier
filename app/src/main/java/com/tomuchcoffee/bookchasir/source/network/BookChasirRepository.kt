package com.tomuchcoffee.bookchasir.source.network

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import com.tomuchcoffee.bookchasir.source.local.ProductDao
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.model.product.Products
import org.koin.dsl.module

val repositoryModule = module {
    factory { BookChasirRepository(get(),get()) }
}

class BookChasirRepository(
    private val api: ApiClient,
    val db : ProductDao

) {

    suspend fun Sigin(
        auth: AuthRequest
    ): AuthResponse {
        return api.auth(auth)
    }

    suspend fun GetAllProduct(
    ): ProductResponse{
        return api.getProductAll()
    }


    suspend fun find(productModel: Products)= db.find(productModel.published)

    suspend fun save(productModel: Products){
        db.save(productModel)
    }

    suspend fun remove(productModel: Products){
        db.remove(productModel)
    }

    suspend fun update(productModel: Products){
        db.update(productModel)
    }

    suspend fun deleteAllDao(){
        db.deletAllProduck()
    }

    suspend fun incrementQty(productModel: Products){
        val producNow = productModel.id?.let { db.getOneProduct(it) }
        Log.d(TAG, "incrementQty: "+ producNow)
        Log.d(TAG, "produk id: "+ productModel)
        if (producNow != null){
            producNow.productbuyqty +=1
//            producNow.productbuyqty++
            db.update(producNow)
        } else{
            productModel.productbuyqty =1
            db.save(productModel)
        }


    }


}