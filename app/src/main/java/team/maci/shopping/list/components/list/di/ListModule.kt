package team.maci.shopping.list.components.list.di

import androidx.recyclerview.widget.ItemTouchHelper
import dagger.Module
import dagger.Provides
import team.maci.shopping.list.components.list.util.ListItemTouchHelperCallback
import team.maci.shopping.list.di.ActivityScope

@Module
class ListModule {

    @ActivityScope
    @Provides
    fun provideItemTouchHelper(
        listItemTouchHelperCallback: ListItemTouchHelperCallback
    ) = ItemTouchHelper(listItemTouchHelperCallback)
}