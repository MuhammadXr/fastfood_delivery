package uz.gita.core.data

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.core.data.models.UserData

object Mapper {

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

    fun DocumentSnapshot.toUserData() = UserData(
        id = this.id,
        name = this["name"].toString(),
        location = this["location"].toString(),
        imgUrl = this["imgUrl"].toString(),
        signed = this["signed"].toString().toBoolean()
    )
}