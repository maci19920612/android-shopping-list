package team.maci.shopping.list.components.edit.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import team.maci.shopping.list.manager.ShoppingListDataManager
import timber.log.Timber
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val shoppingListDataManager: ShoppingListDataManager,
    private val lazyEditView: Lazy<IEditView>
) : ViewModel(){


    var entry = lazyEditView.get().getShoppingListItemParameter()
    var loading = ObservableField(false)
    var entryTitle = ObservableField(entry.title)

    private var saveDisposable: Disposable? = null

    fun onSaveButtonClicked(){
        loading.set(true)
        entry.title = entryTitle.get()!!
        saveDisposable = shoppingListDataManager
            .save(entry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    entry = it
                    loading.set(false)
                    lazyEditView.get().close()
                },
                {
                    loading.set(false)
                    Timber.e(it, "Error happened when we try to save the shopping list entry")
                }
            )
    }

    fun onDestroy(){
        saveDisposable?.dispose()
    }
}