package uz.gita.core.data

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita.core.data.models.*

object Mapper {

    fun DocumentSnapshot.toCategoryData() = CategoryData(
        id = this.id,
        name = this["name"].toString(),
        relevance = this["relevance"].toString().toInt()
    )

    fun DocumentSnapshot.toProductData() = ProductData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString(),
        category = this["category"].toString()
    )

    fun DocumentSnapshot.toStoreData() = StoreData(
        id = this.id,
        name = this["name"].toString(),
        location = this["location"].toString(),
        imgUrl = this["imgUrl"].toString(),
        signed = this["signed"].toString().toBoolean(),
        contact = this["contact"].toString()
    )
    fun DocumentSnapshot.toClientData() = ClientData(
        id = this.id,
        name = this["name"].toString(),
        location = this["location"].toString(),
        imgUrl = this["imgUrl"].toString(),
        signed = this["signed"].toString().toBoolean(),
        contact = this["contact"].toString(),
        selectedStoreId = this["selectedStoreId"].toString()
    )

    fun DocumentSnapshot.toOrderData() = OrderData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString(),
        category = this["category"].toString(),
        count = this["count"].toString().toInt(),
        clientId = this["clientId"].toString(),
        comment = this["comment"].toString(),
        date = this["date"].toString(),
    )

    fun DocumentSnapshot.toDeliveryData() = DeliveryData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString(),
        category = this["category"].toString(),
        count = this["count"].toString().toInt(),
        clientId = this["clientId"].toString(),
        comment = this["comment"].toString(),
        dateAccept = this["dateAccept"].toString(),
        dateDelivery = this["dateDelivery"].toString(),
    )

    fun DocumentSnapshot.toArchivedOrderData() = ArchivedOrderData(
        id = this.id,
        name = this["name"].toString(),
        price = this["price"].toString().toLong(),
        info = this["info"].toString(),
        imgUrl = this["imgUrl"].toString(),
        category = this["category"].toString(),
        count = this["count"].toString().toInt(),
        clientId = this["clientId"].toString(),
        comment = this["comment"].toString(),
        dateAccept = this["dateAccept"].toString(),
        dateDelivery = this["dateDelivery"].toString(),
    )
}