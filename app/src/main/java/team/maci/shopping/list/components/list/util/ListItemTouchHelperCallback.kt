package team.maci.shopping.list.components.list.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.di.ActivityScope
import timber.log.Timber
import javax.inject.Inject


@ActivityScope
class ListItemTouchHelperCallback @Inject constructor(
    private val viewModel: ListViewModel
): ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0){



    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val originalPosition = viewHolder.adapterPosition
        val targetPosition = target.adapterPosition

        Timber.d("Item move, original position: $originalPosition, targetPosition: $targetPosition")
        return viewModel.itemMoved(originalPosition, targetPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //Empty implementation
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
    }


}