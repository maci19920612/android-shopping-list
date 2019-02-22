package team.maci.shopping.list.components.edit.di

import dagger.Binds
import dagger.Module
import team.maci.shopping.list.components.edit.EditActivity
import team.maci.shopping.list.components.edit.viewmodel.IEditView

@Module
abstract class EditModule{
    @Binds
    abstract fun bindView(editActivity: EditActivity) : IEditView
}