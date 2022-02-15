package com.example.cirestechnologiesmobilechallenge.di

import android.content.Context
import com.example.cirestechnologiesmobilechallenge.core.util.SharedPreference
import com.example.cirestechnologiesmobilechallenge.data.remote.repository.NewsServices
import com.example.cirestechnologiesmobilechallenge.data.remote.service.NewsServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.BODY
                logger = Logger.DEFAULT
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreference {
        return SharedPreference(context)
    }

    @Provides
    @Singleton
    fun provideNews(client: HttpClient): NewsServices {
        return NewsServiceImpl(client)
    }

}