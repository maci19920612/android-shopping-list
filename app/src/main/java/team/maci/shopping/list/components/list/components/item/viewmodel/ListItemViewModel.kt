package team.maci.shopping.list.components.list.components.item.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.util.PropertyWatcher

class ListItemViewModel(
    private val parentViewModel: ListViewModel
): ViewModel(){
    private var item: ShoppingListItem? = null
    private var activePropertyWatcher = PropertyWatcher {
        onActiveCheckboxChanged(itemActive.get() ?: false)
    }

    val itemTitle = ObservableField("")
    val itemActive = ObservableField(false)

    init {
        itemActive.addOnPropertyChangedCallback(activePropertyWatcher)
    }

    fun setItem(item: ShoppingListItem){
        activePropertyWatcher.enabled = false
        this.item = item
        itemTitle.set(item.title)
        itemActive.set(item.active)
        activePropertyWatcher.enabled = true
    }

    fun onEditButtonClick(){
        val item = item ?: return
        parentViewModel.itemEdit(item)
    }

    fun onRemoveButtonClick(){
        val item = item ?: return
        parentViewModel.itemRemove(item)
    }

    fun onActiveCheckboxChanged(active: Boolean){
        val item = item ?: return
        item.active = active
        parentViewModel.activeItemChanged(item)
    }
}



