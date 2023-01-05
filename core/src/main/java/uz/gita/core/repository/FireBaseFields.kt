package uz.gita.core.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.gita.core.data.Mapper.toClientData


const val STORES_FOLDER = "stores"
const val USERS_FOLDER = "users"
const val PRODUCT_FOLDER = "products"
const val ORDER_FOLDER = "orders"
const val DELIVERY_FOLDER = "delivery"
const val CATEGORIES_FOLDER = "categories"
const val ARCHIVED_FOLDER = "delivery"
const val USERS_CART_FOLDER = "cart"

object FireBaseFields {
    val db by lazy { Firebase.firestore }
    val user by lazy { Firebase.auth.currentUser!! }
    val storeCollection by lazy { db.collection(STORES_FOLDER) }
    val userCollection by lazy { db.collection(USERS_FOLDER) }
    val clientData by lazy { userCollection.document(user.uid) }
    val storeId by lazy { clientData.get().result.toClientData().selectedStoreId }
    val storeData by lazy { storeCollection.document(user.uid) }
    val cartCollection by lazy { clientData.collection(USERS_CART_FOLDER) }
    val archivedCollection by lazy { storeCollection.document(user.uid).collection(ARCHIVED_FOLDER) }
    val storeDelivery by lazy { storeCollection.document(user.uid).collection(DELIVERY_FOLDER) }
    val storeOrders by lazy { storeCollection.document(user.uid).collection(ORDER_FOLDER) }
    val clientOrders by lazy { storeCollection.document(storeId).collection(ORDER_FOLDER) }
    val storeProducts by lazy { storeCollection.document(user.uid).collection(PRODUCT_FOLDER) }
    val auth by lazy { Firebase.auth }
    val storeCategories by lazy { storeCollection.document(user.uid).collection(CATEGORIES_FOLDER) }
    val clientCategories by lazy { storeCollection.document(storeId).collection(CATEGORIES_FOLDER) }

}