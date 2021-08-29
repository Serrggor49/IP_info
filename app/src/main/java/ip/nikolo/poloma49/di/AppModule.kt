package ip.nikolo.poloma49.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ip.nikolo.poloma49.MyApplication
import ip.nikolo.poloma49.domain.GetDataRepository
import ip.nikolo.poloma49.model.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Singleton
    @Provides
    fun getRepository(): GetDataRepository {
        return Repository()
    }

}