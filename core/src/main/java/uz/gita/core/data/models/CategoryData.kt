package uz.gita.core.data.models

import java.util.UUID

data class CategoryData(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
)
