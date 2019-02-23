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

@ActivityScope
class ListViewModel @Inject constructor(
    val adapter: ShoppingListAdapter,
    private val dataManager: ShoppingListDataManager,
    private val lazyView: Lazy<IListView>

) : ViewModel() {
    var loading = ObservableField(false)
    private var removeDisposable: Disposable? = null
    private var listDisposable: Disposable? = null
    private var updateDisposable: Disposable? = null


    fun itemRemove(item: ShoppingListItem) {
        loading.set(true)
        removeDisposable = dataManager
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

    fun itemEdit(item: ShoppingListItem) {
        lazyView.get().startEditScreen(item)
    }


    fun onCreate() {
        listDisposable = dataManager
            .getShoppingListItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapter.setItems(it)
                }, {
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
        updateDisposable = dataManager
            .save(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapter.updateItem(item)
                },
                {
                    item.active = !item.active
                    adapter.updateItem(item)
                    Timber.e(it, "Error while we try to update a shopping list item")
                }
            )
    }

    fun itemMoved(originalPosition: Int, targetPosition: Int): Boolean {
        adapter.moveItemTo(originalPosition, targetPosition)
        return true
    }

    fun itemMoveFinished(){
        val items = adapter.getItems()
        dataManager
            .updateMultipleItems(*items)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //Ignored
                    Timber.d("Order save completed")
                }, {
                    Timber.e(it, "Error while we try to save the order")
                }
            )
    }

    fun onItemAddButtonClick() {
        lazyView.get().startItemCreateScreen()
    }

    fun onDelimiterAddButtonClick(){
        lazyView.get().startDelimiterCreateScreen()
    }

    fun onEditResult(item: ShoppingListItem) {
        adapter.updateItem(item)
    }
}