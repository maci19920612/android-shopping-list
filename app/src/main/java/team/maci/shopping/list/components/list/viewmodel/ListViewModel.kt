package team.maci.shopping.list.components.list.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import team.maci.shopping.list.components.list.components.item.ShoppingListAdapter
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.di.ActivityScope
import team.maci.shopping.list.manager.ShoppingListDataManager
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScope
class ListViewModel @Inject constructor(
    val adapter: ShoppingListAdapter,
    private val shoppingListDataManager: ShoppingListDataManager,
    private val lazyView: Lazy<IListView>

) : ViewModel() {
    var loading = ObservableField(false)
    private var removeDisposable: Disposable? = null
    private var listDisposable : Disposable? = null
    private var updateDisposable : Disposable? = null


    fun itemRemove(item: ShoppingListItem) {
        loading.set(true)
        removeDisposable = shoppingListDataManager
            .remove(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading.set(false)
                    adapter.removeItem(item)
                }, {
                    loading.set(false)
                    Timber.e(it, "Error when we try to remove an item")
                }
            )
    }

    fun itemEdit(item: ShoppingListItem){
        lazyView.get().startEditScreen(item)
    }

    fun onItemAddButtonClick(){
        lazyView.get().startCreateScreen()
    }

    fun onEditResult(item: ShoppingListItem){
        adapter.updateItem(item)
    }

    fun onCreate(){
        //loading.set(true)
        listDisposable = shoppingListDataManager
            .getShoppingListItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading.set(false)
                    adapter.setItems(it)
                }, {
                    loading.set(false)
                    Timber.e(it, "Error while we try to list the shopping list items")
                }
            )
    }

    fun onDestroy() {
        removeDisposable?.dispose()
        updateDisposable?.dispose()
        listDisposable?.dispose()
    }

    fun activeItemChanged(item: ShoppingListItem) {
        loading.set(true)
        updateDisposable = shoppingListDataManager
            .save(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading.set(false)
                    adapter.updateItem(item)
                },
                {
                    loading.set(false)
                    item.active = !item.active
                    adapter.updateItem(item)
                    Timber.e(it, "Error while we try to update a shopping list item")
                }
            )
    }
}