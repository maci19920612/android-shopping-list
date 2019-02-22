package team.maci.shopping.list.components.list.viewmodel

import team.maci.shopping.list.database.entity.ShoppingListItem

interface IListView{
    fun startEditScreen(item: ShoppingListItem)

    fun startCreateScreen()
}