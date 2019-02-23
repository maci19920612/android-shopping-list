package team.maci.shopping.list.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import team.maci.shopping.list.database.entity.ShoppingListItem
import java.util.*

@Dao
interface ShoppingListDao{
    @Query("SELECT * FROM shopping_list")
    fun getAllEntries() : Single<List<ShoppingListItem>>

    @Query("DELETE FROM shopping_list WHERE inactivatedDate < :threshold AND NOT active")
    fun deleteAllOutdatedEntry(threshold: Date) : Completable

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    fun getItem(id: Long) : Single<ShoppingListItem>

    @Insert
    fun create(entry: ShoppingListItem) : Single<Long>

    @Update
    fun update(entry: ShoppingListItem) : Completable

    @Update
    fun update(vararg entry: ShoppingListItem) : Completable

    @Delete
    fun remove(entry: ShoppingListItem) : Completable
}