package uz.gita.core.repository

import uz.gita.core.repository.internal.*
import uz.gita.core.repository.internal.store.*
import uz.gita.core.repository.internal.store.impl.*
import uz.gita.core.repository.internal.store.impl.CategoriesRepositoryImpl
import uz.gita.core.repository.impl.FilesRepositoryImpl


object StoreRepository {
    val productsRepository: ProductsRepository by lazy { ProductsRepositoryImpl() }
    val categoriesRepository: CategoriesRepository by lazy { CategoriesRepositoryImpl() }
    val filesRepository: FilesRepository by lazy { FilesRepositoryImpl() }
    val storeAuthRepository: StoreAuthRepository by lazy { StoreAuthRepositoryImpl() }
    val storeProfileRepository: StoreProfileRepository by lazy { StoreProfileRepositoryImpl() }
    val ordersRepository: OrdersRepository by lazy { OrdersRepositoryImpl() }
    val deliveryRepository: DeliveryRepository by lazy { DeliveryRepositoryImpl() }
    val archivedRepository: ArchivedRepository by lazy { ArchivedRepositoryImpl() }

}