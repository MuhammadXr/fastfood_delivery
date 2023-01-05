package uz.gita.core.repository.internal.client

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.ProductData

interface ClientCartRepository {
    fun addToCart(productData: ProductData): Flow<Result<Unit>>
    fun removeFromCart(productData: ProductData): Flow<Result<Unit>>
}