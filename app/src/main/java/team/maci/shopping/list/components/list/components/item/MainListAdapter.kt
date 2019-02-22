package team.maci.shopping.list.components.list.components.item

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.maci.shopping.list.database.entity.ShoppingListItem


class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ShoppingListViewModel, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun removeItem(shoppingListItem: ShoppingListItem){}
    fun updateItem(item: ShoppingListItem) {
    }
}