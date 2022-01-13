package bit.hemant.kmmfood2fork.datasource.cache

import android.content.Context
import bit.hemant.kmpfood2fork.datasource.cache.RecipeDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(RecipeDatabase.Schema, context, "recipes.db")
    }
}