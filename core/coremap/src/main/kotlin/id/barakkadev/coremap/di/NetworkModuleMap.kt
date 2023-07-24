package id.barakkadev.coremap.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.barakkadev.coremap.BuildConfig
import id.barakkadev.coremap.data.datasource.remote.MapApiService
import id.barakkadev.coremap.network.interceptor.HttpRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/** Created by github.com/im-o on 7/16/2023. */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleMap {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    @Named("okHttpClientMap")
    fun provideOkHttpClientMap(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedRequest = originalRequest.newBuilder()
                    .url(
                        originalRequest.url.newBuilder()
                            .addQueryParameter("key", BuildConfig.MAPS_API_KEY)
                            .build()
                    )
                    .build()
                chain.proceed(modifiedRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofitMap")
    fun provideRetrofitMap(converterFactory: GsonConverterFactory, @Named("okHttpClientMap") okHttpClientMap: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(converterFactory)
            .client(okHttpClientMap)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServiceMap(@Named("retrofitMap") retrofitMap: Retrofit): MapApiService {
        return retrofitMap.create(MapApiService::class.java)
    }
}