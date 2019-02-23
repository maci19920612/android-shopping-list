package team.maci.shopping.list.components.list.components.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import team.maci.shopping.list.R
import team.maci.shopping.list.components.list.components.item.viewmodel.ListItemViewModel
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.di.ActivityScope
import timber.log.Timber
import javax.inject.Inject


@ActivityScope
class ShoppingListAdapter @Inject constructor(
    private val lazyListViewModel: Lazy<ListViewModel>,
    private val itemTouchHelper: Lazy<ItemTouchHelper>
) : RecyclerView.Adapter<ShoppingListViewHolder>() {
    private val items: MutableList<ShoppingListItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: team.maci.shopping.list.databinding.ItemShopingListEntryBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_shoping_list_entry, parent, false)
        val viewModel = ListItemViewModel(lazyListViewModel.get())
        val viewHolder = ShoppingListViewHolder(binding.root, binding, viewModel)
        viewModel.setDragListener {
            Timber.d("Invoke drag listener, viewHolder: $viewHolder")
            itemTouchHelper.get().startDrag(viewHolder)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val model = items[position]
        holder.viewModel.setItem(model)
        holder.binding.model = holder.viewModel
        holder.binding.executePendingBindings()
    }

    fun removeItem(item: ShoppingListItem) {
        val targetIndex = items.indexOfFirst { it.id == item.id }
        if (targetIndex >= 0) {
            items.removeAt(targetIndex)
            notifyItemRemoved(targetIndex)
        }
    }

    fun updateItem(item: ShoppingListItem) {
        val targetIndex = items.indexOfFirst { it.id == item.id }
        if (targetIndex >= 0) {
            items[targetIndex] = item
        } else {
            items.add(item)
        }

        this.items.sort()
        notifyDataSetChanged()
    }

    fun setItems(items: List<ShoppingListItem>) {
        this.items.clear()
        this.items.addAll(items)
        this.items.sort()

        notifyDataSetChanged()
    }

    fun moveItemTo(originalPosition: Int, targetPosition: Int) {
        val originalItem = items[originalPosition]
        if(originalPosition < targetPosition){
            for(i in originalPosition + 1..targetPosition){
                items[i - 1] = items[i]
            }
        }else if(originalPosition > targetPosition){
            for(i in originalPosition - 1 downTo targetPosition){
                items[i + 1] = items[i]
            }
        }
        items[targetPosition] = originalItem

        //Update all items order
        for(i in 0 until items.size){
            items[i].order = i + 1
        }

        notifyItemMoved(originalPosition, targetPosition)
    }

    fun getItems() : Array<ShoppingListItem>{
        return items.toTypedArray()
    }
}