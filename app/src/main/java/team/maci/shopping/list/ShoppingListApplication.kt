package team.maci.shopping.list

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import team.maci.shopping.list.di.ComponentContainer
import team.maci.shopping.list.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class ShoppingListApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: AndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        ComponentContainer.init(component)

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}