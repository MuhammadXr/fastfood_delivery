package uz.gita.core.repository

import uz.gita.core.repository.impl.CategoriesRepositoryImpl
import uz.gita.core.repository.impl.ItemsRepositoryImpl
import uz.gita.core.repository.impl.OrderRepositoryImpl

object Repository {
    val orderRepository: OrderRepository by lazy { OrderRepositoryImpl() }
    val itemsRepository: ItemsRepository by lazy { ItemsRepositoryImpl() }
    val categoriesRepository: CategoriesRepository by lazy { CategoriesRepositoryImpl() }
}