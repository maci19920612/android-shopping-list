package team.maci.shopping.list.components.list.di

import dagger.Binds
import dagger.Module
import team.maci.shopping.list.components.list.ListActivity
import team.maci.shopping.list.components.list.viewmodel.IListView

@Module
abstract class ListBinderModule {
    @Binds
    abstract fun bindView(listActivity: ListActivity): IListView
}

