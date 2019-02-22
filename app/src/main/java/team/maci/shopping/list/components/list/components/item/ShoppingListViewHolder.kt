package team.maci.shopping.list.components.list.components.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import team.maci.shopping.list.components.list.components.item.viewmodel.ListItemViewModel

class ShoppingListViewHolder(
    itemView: View,
    val binding: team.maci.shopping.list.databinding.ItemShopingListEntryBinding,
    val viewModel: ListItemViewModel
) : RecyclerView.ViewHolder(itemView)
