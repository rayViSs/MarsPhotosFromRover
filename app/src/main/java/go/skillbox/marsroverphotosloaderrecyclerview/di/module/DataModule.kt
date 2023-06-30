package go.skillbox.marsroverphotosloaderrecyclerview.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import go.skillbox.data.Repository
import go.skillbox.domain.repository.MarsPhotosRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun getRepository(): MarsPhotosRepository {
        return Repository()
    }
}
//MarsPhotosNasaAPI-master