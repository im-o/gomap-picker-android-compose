package id.barakkadev.coremap.di.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.barakkadev.coremap.domain.repository.googlemap.GoogleMapRepository
import id.barakkadev.coremap.domain.usecase.googlemap.ReverseGeocodeUseCase

/** Created by github.com/im-o on 7/16/2023. */

@Module
@InstallIn(ViewModelComponent::class)
object GoogleMapUseCaseModule {
    @Provides
    fun provideReverseGeocodeUseCase(repository: GoogleMapRepository): ReverseGeocodeUseCase {
        return ReverseGeocodeUseCase(repository)
    }
}