package uz.gita.core.repository.internal.store

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.ProductData

interface ProductsRepository {
    fun placingProducts(productData: ProductData) : Flow<Result<Unit>>
    fun getProducts() : Flow<Result<List<ProductData>>>
    fun getProductsRealTime(): Flow<List<ProductData>>
}