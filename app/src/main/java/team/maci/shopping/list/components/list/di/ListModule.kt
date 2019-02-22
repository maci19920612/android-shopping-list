package team.maci.shopping.list.components.list.di

import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import team.maci.shopping.list.components.list.ListActivity
import team.maci.shopping.list.components.list.components.item.ShoppingListAdapter
import team.maci.shopping.list.components.list.viewmodel.IListView
import team.maci.shopping.list.components.list.viewmodel.ListViewModel
import team.maci.shopping.list.manager.ShoppingListDataManager

@Module
abstract class ListModule{
    @Binds
    abstract fun bindView(listActivity: ListActivity) : IListView


    @Module
    companion object {
        @Provides
        fun provideListViewModel(adapter: ShoppingListAdapter, listView: Lazy<IListView>, shoppingListDataManager: ShoppingListDataManager) : ListViewModel{
            return ListViewModel(adapter, shoppingListDataManager, listView)
        }
    }
}