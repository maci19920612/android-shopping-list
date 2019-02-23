package team.maci.shopping.list.components.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.android.AndroidInjection
import team.maci.shopping.list.R
import team.maci.shopping.list.components.edit.EditActivity
import team.maci.shopping.list.components.list.viewmodel.IListView
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.databinding.ActivityListBinding
import javax.inject.Inject

class ListActivity : AppCompatActivity(), IListView{

    @Inject
    lateinit var viewModel: ListViewModel
    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        binding.model = viewModel

        itemTouchHelper.attachToRecyclerView(binding.shoppingList)

        viewModel.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun startEditScreen(item: ShoppingListItem) {
        startActivityForResult(EditActivity.newIntent(this, item), REQUEST_CODE_EDIT)
    }

    override fun startItemCreateScreen() {
        startActivityForResult(EditActivity.newIntent(this), REQUEST_CODE_EDIT)
    }

    override fun startDelimiterCreateScreen() {
        startActivityForResult(EditActivity.newIntent(
            context = this,
            isDelimiter = true
        ), REQUEST_CODE_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null){
            val resultItem = EditActivity.getShoppingListItemResult(data) ?: return
            viewModel.onEditResult(resultItem)
        }
    }

    companion object {
        private val REQUEST_CODE_EDIT = 1
    }
}
