package uz.gita.core.data.models

import java.util.UUID

data class OrderData(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val foodCollection: List<Map<String, Int>>,
    val price: Long,
    val info: String,
    val imgUrl: String,
)
