package com.morimagno.grupo3a.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse(

    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<ProductResponse>
)
