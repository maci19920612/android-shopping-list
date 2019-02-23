package team.maci.shopping.list.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import team.maci.shopping.list.database.Database


@Module
class AppModule{

    @Provides
    fun provideDatabase(app: Application) : Database{
        return Room
            .databaseBuilder(app,Database::class.java, "shopping-list")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideShoppingDaoDao(database: Database) = database.getShoppingListDao()
}