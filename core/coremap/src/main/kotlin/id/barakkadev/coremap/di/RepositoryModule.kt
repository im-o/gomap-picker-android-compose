package id.barakkadev.coremap.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.barakkadev.coremap.data.datasource.remote.MapApiService
import id.barakkadev.coremap.data.repository.googlemap.GoogleMapRepositoryImpl
import id.barakkadev.coremap.domain.repository.googlemap.GoogleMapRepository
import javax.inject.Singleton

/** Created by github.com/im-o on 12/17/2022. */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGoogleMapRepository(mapApiService: MapApiService): GoogleMapRepository {
        return GoogleMapRepositoryImpl(mapApiService)
    }
}