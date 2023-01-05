package uz.gita.core.data.models

import java.util.*

data class ArchivedOrderData(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "empty name",
    val price: Long = 0,
    val count: Int = 0,
    val category: String = "food",
    val info: String = "info temporarily unavailable",
    var imgUrl: String = "",
    var clientId: String = "0",
    var comment: String = "",
    var dateAccept: String = "unavailable",
    var dateDelivery: String = "unavailable",
)
