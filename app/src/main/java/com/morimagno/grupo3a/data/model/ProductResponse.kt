package com.morimagno.grupo3a.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(
    @SerializedName("productId") val productId: Int,
    @SerializedName("productName") val productName: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("markedPrice") val markedPrice: Double,
    @SerializedName("sellingPrice") val sellingPrice: Double,
    @SerializedName("reviewCount") val reviewCount: Int,
    @SerializedName("totalRating") val totalRating: Int,
    @SerializedName("reviews") val reviews: List<ReviewResponse>
)
    : Serializable {
    fun getProductImage(): String =
        "${imageUrl}"
}

data class ReviewResponse(
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("comment") val comment: String
)
