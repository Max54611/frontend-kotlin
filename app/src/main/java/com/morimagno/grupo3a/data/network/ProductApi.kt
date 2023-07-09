package com.morimagno.grupo3a.data.network

import com.morimagno.grupo3a.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Response<ProductResponse>
}