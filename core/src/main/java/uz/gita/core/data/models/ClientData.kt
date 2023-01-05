package uz.gita.core.data.models

import java.util.UUID

data class ClientData(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Empty",
    val location: String = "Empty",
    val contact: String = "Empty",
    var imgUrl: String = "",
    val signed: Boolean = false,
    var selectedStoreId: String = "",
)
