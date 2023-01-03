package uz.gita.core.data.models

data class UserData(
    val id: String,
    val name: String,
    val location: String,
    val imgUrl: String,
    val signed: Boolean = false
)
