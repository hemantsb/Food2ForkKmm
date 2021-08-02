package app.bit.kmpfood2fork.presentation.recipe_list

sealed class RecipeListEvent{
    object  LoadRecipes : RecipeListEvent()
    object  NextPage : RecipeListEvent()
    object  NewSearch : RecipeListEvent()
    data class  UpdateQuery(val query: String) : RecipeListEvent()
}
