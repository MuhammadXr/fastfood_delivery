package uz.gita.core.data.models

import java.util.UUID

data class OrderData(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "empty name",
    val price: Long = 0,
    val info: String = "info temporarily unavailable",
    val imgUrl: String = "",
)
