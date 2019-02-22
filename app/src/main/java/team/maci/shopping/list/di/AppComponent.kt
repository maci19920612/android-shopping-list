package team.maci.shopping.list.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import team.maci.shopping.list.ShoppingListApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, AppModule::class])
interface AppComponent : AndroidInjector<ShoppingListApplication> {

    @Component.Builder
    abstract class Builder{
        @BindsInstance
        abstract fun application(application: Application) : Builder
        abstract fun build() : AppComponent
    }
}