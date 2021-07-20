package app.bit.kmpfood2fork.android.di

import app.bit.kmpfood2fork.datasource.cache.RecipeCache
import app.bit.kmpfood2fork.datasource.network.RecipeService
import app.bit.kmpfood2fork.interactors.recipe_detail.GetRecipeUseCase
import app.bit.kmpfood2fork.interactors.recipe_list.SearchRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache
    ): SearchRecipeUseCase {
        return SearchRecipeUseCase(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipeUseCase {
        return GetRecipeUseCase(recipeCache = recipeCache)
    }
}