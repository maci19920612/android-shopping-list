package team.maci.shopping.list.components.list.components.item.viewmodel

import androidx.lifecycle.ViewModel
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.database.entity.ShoppingListItem

class ListItemViewModel(
    val parentViewModel: ListViewModel
): ViewModel(){
    lateinit var item: ShoppingListItem

    fun onEditButtonClick(){
        parentViewModel.itemEdit(item)
    }

    fun onRemoveButtonClick(){
        parentViewModel.itemRemove(item)
    }

    fun onActiveCheckboxChanged(isChecked: Boolean){
        item.active = !isChecked
        parentViewModel.activeItemChanged(item)
    }
}