package team.maci.shopping.list.manager

import io.reactivex.Completable
import io.reactivex.Single
import team.maci.shopping.list.database.dao.ShoppingListDao
import team.maci.shopping.list.database.entity.ShoppingListItem
import java.util.*
import javax.inject.Singleton

@Singleton
class ShoppingListDataManager(
    val shoppingListDao: ShoppingListDao
){
    fun save(entry: ShoppingListItem) : Completable{
        //We have to update the widget
        return if(entry.id == 0){
            shoppingListDao
                .create(entry)
                .doOnComplete {
                    notifyWidget()
                }

        }else{
            shoppingListDao.update(entry)
                .doOnComplete {
                    notifyWidget()
                }
        }
    }

    fun remove(entry: ShoppingListItem) : Completable{
        return shoppingListDao
            .remove(entry)
            .doOnComplete {
                notifyWidget()
            }
    }

    fun getShoppingListItems() : Single<List<ShoppingListItem>>{
        return shoppingListDao.getAllEntries()
    }

    fun removeInactiveItems() : Completable{
        val targetTimestamp = Date().time - 1000 * 60 * 60 * 24
        return shoppingListDao
            .deleteAllOutdatedEntry(Date(targetTimestamp))
            .doOnComplete {
                notifyWidget()
            }
    }

    private fun notifyWidget(){
        //TODO: Update widget
    }
}