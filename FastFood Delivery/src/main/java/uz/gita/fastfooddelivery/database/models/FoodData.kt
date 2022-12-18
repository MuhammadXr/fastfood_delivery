package uz.gita.fastfooddelivery.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodData(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Long,
    val info: String,
    val imgUrl:String,
)
