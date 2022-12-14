package uz.gita.core.data.models

import java.util.UUID

data class ProductData(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "empty name",
    val price: Long = 0,
    val category: String = "food",
    val info: String = "info temporarily unavailable",
    var imgUrl: String = "",
): java.io.Serializable
