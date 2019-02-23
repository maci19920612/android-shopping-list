package team.maci.shopping.list.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import team.maci.shopping.list.database.converters.DateTypeConverter
import team.maci.shopping.list.database.dao.ShoppingListDao
import team.maci.shopping.list.database.entity.ShoppingListItem


@Database(entities = [ShoppingListItem::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class Database : RoomDatabase(){
    abstract fun getShoppingListDao() : ShoppingListDao
}