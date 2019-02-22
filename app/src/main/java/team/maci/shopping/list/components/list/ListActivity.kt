package team.maci.shopping.list.components.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import team.maci.shopping.list.R
import team.maci.shopping.list.components.edit.EditActivity
import team.maci.shopping.list.components.list.viewmodel.IListView
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.database.entity.ShoppingListItem
import javax.inject.Inject

class ListActivity : AppCompatActivity(), IListView{

    @Inject
    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        AndroidInjection.inject(this)

    }

    override fun startEditScreen(item: ShoppingListItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startCreateScreen() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null){
            val resultItem = EditActivity.getShoppingListItemResult(data) ?: return

        }
    }

    companion object {
        private val REQUEST_CODE_EDIT = 1
    }
}
