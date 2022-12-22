package uz.gita.core.data

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.FoodData
import uz.gita.core.data.models.OrderData

object Mapper {
    fun DocumentSnapshot.toFoodData() = FoodData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString()
    )

    fun DocumentSnapshot.toCategoryData() = CategoryData(
        id = this.id,
        name = this["name"].toString(),
        relevance = this["relevance"].toString().toInt()
    )

    fun DocumentSnapshot.toOrderData() = OrderData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString(),
        category = this["category"].toString()
    )
}