package uz.gita.core.repository

import uz.gita.core.repository.impl.CategoriesRepositoryImpl
import uz.gita.core.repository.impl.FilesRepositoryImpl
import uz.gita.core.repository.impl.OrderRepositoryImpl

object Repository {
    val orderRepository: OrderRepository by lazy { OrderRepositoryImpl() }
    val categoriesRepository: CategoriesRepository by lazy { CategoriesRepositoryImpl() }
    val filesRepository: FilesRepository by lazy { FilesRepositoryImpl() }
}