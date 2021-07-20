package app.bit.kmpfood2fork.android.di

import app.bit.kmpfood2fork.android.Food2ForkApplication
import app.bit.kmpfood2fork.datasource.cache.*
import app.bit.kmpfood2fork.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: Food2ForkApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase,
    ): RecipeCache {
        return RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil(),
        )
    }
}