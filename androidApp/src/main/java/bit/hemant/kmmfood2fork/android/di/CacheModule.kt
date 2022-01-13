package bit.hemant.kmmfood2fork.android.di

import bit.hemant.kmmfood2fork.android.Food2ForkApplication
import bit.hemant.kmmfood2fork.datasource.cache.*
import bit.hemant.kmmfood2fork.domain.util.DatetimeUtil
import bit.hemant.kmpfood2fork.datasource.cache.RecipeDatabase
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