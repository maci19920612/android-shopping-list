package team.maci.shopping.list.components.list.components.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import team.maci.shopping.list.components.list.components.item.viewmodel.ListItemViewModel
import team.maci.shopping.list.databinding.ItemShopingListEntryBinding

class ShoppingListViewHolder(
    itemView: View,
    val binding: ItemShopingListEntryBinding,
    val viewModel: ListItemViewModel
) : RecyclerView.ViewHolder(itemView)
