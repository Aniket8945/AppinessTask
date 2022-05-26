package com.task.appiness.di

import android.content.Context
import com.task.appiness.AppApplication
import com.task.appiness.data.network.AuthApi
import com.task.appiness.data.network.RemoteDataSource
import com.task.appiness.data.repository.AuthRepository
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
    fun provideApplication(@ApplicationContext app: Context): AppApplication {
        return app as AppApplication
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java, context)
    }


    @Provides
    fun provideAuthRepository(
        authApi: AuthApi,
    ): AuthRepository {
        return AuthRepository(authApi)
    }
}