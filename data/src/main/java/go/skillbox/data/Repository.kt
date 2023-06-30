package go.skillbox.data

import go.skillbox.data.retrofit.MarsPhotosDataSource
import go.skillbox.data.util.DtoConverter
import go.skillbox.domain.models.entities.MarsPhotosList
import go.skillbox.domain.models.params.GetPhotosByPageParam
import go.skillbox.domain.repository.MarsPhotosRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor() : MarsPhotosRepository {

    private val networkData = MarsPhotosDataSource()
    private val converter = DtoConverter()

    override suspend fun getPhotosByPage(params: GetPhotosByPageParam): MarsPhotosList {
        delay(2000)
        val photos = networkData.getPhotosByPage(params.pageNumber)
        return converter.convert(photos ?: throw RuntimeException("Null response body"))
    }
}