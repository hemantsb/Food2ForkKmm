package bit.hemant.kmmfood2fork.android.di

import bit.hemant.kmmfood2fork.datasource.cache.RecipeCache
import bit.hemant.kmmfood2fork.datasource.network.RecipeService
import bit.hemant.kmmfood2fork.interactors.recipe_detail.GetRecipe
import bit.hemant.kmmfood2fork.interactors.recipe_list.SearchRecipeUseCase
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
    ): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }
}