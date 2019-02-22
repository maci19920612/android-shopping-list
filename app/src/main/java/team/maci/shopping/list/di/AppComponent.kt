package team.maci.shopping.list.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class, AppModule::class])
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder{
        @BindsInstance
        abstract fun application(application: Application) : Builder
        abstract fun build() : AppComponent
    }
}