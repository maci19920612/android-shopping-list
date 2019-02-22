package team.maci.shopping.list.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import team.maci.shopping.list.components.edit.EditActivity
import team.maci.shopping.list.components.edit.di.EditModule
import team.maci.shopping.list.components.list.ListActivity
import team.maci.shopping.list.components.list.di.ListModule

@Module
abstract class ActivityModule{
    @ActivityScope
    @ContributesAndroidInjector(modules=[ListModule::class])
    abstract fun bindListActivity() : ListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [EditModule::class])
    abstract fun bindEditActivity() : EditActivity


}