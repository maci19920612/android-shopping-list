package team.maci.shopping.list.components.edit.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import team.maci.shopping.list.manager.ShoppingListDataManager
import timber.log.Timber
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val shoppingListDataManager: ShoppingListDataManager,
    private val editView: IEditView
) : ViewModel(){


    var entry = editView.getShoppingListItemParameter()
    var loading: Boolean = false

    private var saveDisposable: Disposable? = null

    fun onSaveButtonClicked(){
        loading = true
        saveDisposable = shoppingListDataManager
            .save(entry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading = false
                    editView.close()
                },
                {
                    loading = false
                    Timber.e(it, "Error happened when we try to save the shopping list entry")
                }
            )
    }

    fun onDestroy(){
        saveDisposable?.dispose()
    }
}