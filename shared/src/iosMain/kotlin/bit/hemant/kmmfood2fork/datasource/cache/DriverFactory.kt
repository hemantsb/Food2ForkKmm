package bit.hemant.kmmfood2fork.datasource.cache

import bit.hemant.kmpfood2fork.datasource.cache.RecipeDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(RecipeDatabase.Schema, "recipe.db")
    }
}