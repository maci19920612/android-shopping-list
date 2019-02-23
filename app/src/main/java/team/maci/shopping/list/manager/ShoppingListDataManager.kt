package team.maci.shopping.list.manager

import io.reactivex.Completable
import io.reactivex.Single
import team.maci.shopping.list.database.dao.ShoppingListDao
import team.maci.shopping.list.database.entity.ShoppingListItem
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListDataManager @Inject constructor(
    val dao: ShoppingListDao
){
    fun save(entry: ShoppingListItem) : Single<ShoppingListItem>{
        //We have to update the widget
        return if(entry.id == null){
            dao
                .create(entry)
                .doOnSuccess {
                    notifyWidget()
                }.flatMap { dao.getItem(it) }

        }else{
            if(!entry.active){
                entry.inactivatedDate = Date()
            }else{
                entry.inactivatedDate = null
            }
            dao.update(entry)
                .doOnComplete{
                    notifyWidget()
                }
                .andThen(dao.getItem(entry.id.toLong()))
        }
    }

    fun remove(entry: ShoppingListItem) : Completable{
        return dao
            .remove(entry)
            .doOnComplete {
                notifyWidget()
            }
    }

    fun getShoppingListItems() : Single<List<ShoppingListItem>>{
        return dao.getAllEntries()
    }

    fun removeInactiveItems() : Completable{
        val targetTimestamp = Date().time - 1000 * 60 * 60 * 24
        return dao
            .deleteAllOutdatedEntry(Date(targetTimestamp))
            .doOnComplete {
                notifyWidget()
            }
    }

    fun updateMultipleItems(vararg items: ShoppingListItem) : Completable{
        return Completable.merge(items.map { dao.update(it) })
    }

    private fun notifyWidget(){
        //TODO: Update widget
    }
}