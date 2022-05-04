package bit.hemant.kmmfood2fork.presentation.recipe_list

class FoodCategoryUtil {
    fun getAllFoodCategories(): List<FoodCategory> {
        return listOf(
            FoodCategory.ERROR,
            FoodCategory.CHICKEN,
            FoodCategory.BEEF,
            FoodCategory.NOODLES,
            FoodCategory.PASTA,
            FoodCategory.SAMOSA,
            FoodCategory.DESSERT,
            FoodCategory.CAKE,
            FoodCategory.COFFEE,
            FoodCategory.SALAD,
            FoodCategory.BIRYANI,
        )
    }

    fun getFoodCategory(value: String): FoodCategory? {
        val map = FoodCategory.values().associateBy(FoodCategory::value)
        return map[value]
    }
}
