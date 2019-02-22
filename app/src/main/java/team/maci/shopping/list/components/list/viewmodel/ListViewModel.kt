package team.maci.shopping.list.components.list.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import team.maci.shopping.list.components.list.components.item.ShoppingListAdapter
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.manager.ShoppingListDataManager
import timber.log.Timber

class ListViewModel(
    val adapter: ShoppingListAdapter,
    private val shoppingListDataManager: ShoppingListDataManager,
    private val view: IListView

) : ViewModel() {
    var loading: Boolean = false
    private var removeDisposable: Disposable? = null
    fun itemRemove(item: ShoppingListItem) {
        loading = true
        removeDisposable = shoppingListDataManager
            .remove(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading = false
                    adapter.removeItem(item)
                }, {
                    loading = false
                    Timber.e(it, "Error when we try to remove an item")
                }
            )
    }

    fun itemEdit(item: ShoppingListItem){
        view.startEditScreen(item)
    }

    fun onItemAddButtonClick(){
        view.startCreateScreen()
    }

    fun onEditResult(item: ShoppingListItem){
        adapter.updateItem(item)
    }

    fun onDestroy() {
        removeDisposable?.dispose()
    }

    fun activeItemChanged(item: ShoppingListItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}