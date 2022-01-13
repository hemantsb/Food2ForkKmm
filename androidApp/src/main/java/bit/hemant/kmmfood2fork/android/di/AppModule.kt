package bit.hemant.kmmfood2fork.android.di

import android.content.Context
import bit.hemant.kmmfood2fork.android.Food2ForkApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): Food2ForkApplication {
        return app as Food2ForkApplication
    }

}