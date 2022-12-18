package uz.gita.fastfooddelivery.database.dao

import androidx.room.Dao
import uz.gita.fastfooddelivery.database.models.OrderData

@Dao
interface OrderDao: BaseDao<OrderData>{
}