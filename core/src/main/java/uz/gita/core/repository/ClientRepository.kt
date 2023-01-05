package uz.gita.core.repository

import uz.gita.core.repository.internal.*
import uz.gita.core.repository.internal.store.*
import uz.gita.core.repository.internal.store.impl.*
import uz.gita.core.repository.internal.store.impl.CategoriesRepositoryImpl
import uz.gita.core.repository.impl.FilesRepositoryImpl
import uz.gita.core.repository.internal.client.*
import uz.gita.core.repository.internal.client.impl.*
import uz.gita.core.repository.internal.client.impl.ClientAuthRepositoryImpl
import uz.gita.core.repository.internal.client.impl.ClientOrdersRepositoryImpl
import uz.gita.core.repository.internal.client.impl.ClientProfileRepositoryImpl
import uz.gita.core.repository.internal.client.impl.ClientStoresRepositoryImpl


object ClientRepository {
    val filesRepository: FilesRepository by lazy { FilesRepositoryImpl() }
    val clientStoresRepository: ClientStoresRepository by lazy { ClientStoresRepositoryImpl() }
    val clientProfileRepository: ClientProfileRepository by lazy { ClientProfileRepositoryImpl() }
    val clientOrdersRepository: ClientOrdersRepository by lazy { ClientOrdersRepositoryImpl() }
    val clientAuthRepository: ClientAuthRepository by lazy { ClientAuthRepositoryImpl() }
    val clientCategoriesOfProductsRepository: ClientCategoriesOfProductsRepository by lazy { ClientCategoriesOfProductsRepositoryImpl() }
}