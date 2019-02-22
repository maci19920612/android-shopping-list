package team.maci.shopping.list.components.edit.viewmodel

import team.maci.shopping.list.database.entity.ShoppingListItem

interface IEditView{
    fun close()

    fun getShoppingListItemParameter() : ShoppingListItem
}