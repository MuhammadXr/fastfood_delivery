package uz.gita.core.repository

import com.google.firebase.auth.FirebaseUser
import uz.gita.core.repository.impl.*
import uz.gita.core.repository.impl.CategoriesRepositoryImpl
import uz.gita.core.repository.impl.FilesRepositoryImpl
import uz.gita.core.repository.impl.OrderRepositoryImpl
import uz.gita.core.repository.internal.*


object DeliveryRepository {
    val orderRepository: OrderRepository by lazy { OrderRepositoryImpl() }
    val categoriesRepository: CategoriesRepository by lazy { CategoriesRepositoryImpl() }
    val filesRepository: FilesRepository by lazy { FilesRepositoryImpl() }
    val authRepository: AuthRepository by lazy { AuthRepositoryImpl() }
    val profileRepository: ProfileRepository by lazy { ProfileRepositoryImpl() }

}