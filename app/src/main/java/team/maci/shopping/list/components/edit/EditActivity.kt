package team.maci.shopping.list.components.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import team.maci.shopping.list.R
import team.maci.shopping.list.components.edit.viewmodel.EditViewModel
import team.maci.shopping.list.components.edit.viewmodel.IEditView
import team.maci.shopping.list.database.entity.ShoppingListItem
import team.maci.shopping.list.databinding.ActivityEditBinding
import javax.inject.Inject


class EditActivity : AppCompatActivity(), IEditView {
    @Inject
    lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityEditBinding>(this, R.layout.activity_edit)
        binding.model = editViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        editViewModel.onDestroy()
    }

    override fun close() {
        val resultIntent = Intent().putExtra(PARAM_SHOPPING_LIST_ITEM, editViewModel.entry)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun getShoppingListItemParameter(): ShoppingListItem {
        return intent.getSerializableExtra(PARAM_SHOPPING_LIST_ITEM) as? ShoppingListItem ?: ShoppingListItem()
    }

    companion object{
        private val PARAM_SHOPPING_LIST_ITEM = "Param.ShoppingListItem"

        fun newIntent(context: Context, shoppingListItem: ShoppingListItem? = null) : Intent{
            val ret = Intent(context, EditActivity::class.java)
            if(shoppingListItem != null){
                ret.putExtra(PARAM_SHOPPING_LIST_ITEM, shoppingListItem)
            }
            return ret
        }

        fun getShoppingListItemResult(intent: Intent) : ShoppingListItem?{
            return intent.getSerializableExtra(PARAM_SHOPPING_LIST_ITEM) as? ShoppingListItem
        }
    }
}