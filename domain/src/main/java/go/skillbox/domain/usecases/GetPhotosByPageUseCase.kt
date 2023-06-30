package go.skillbox.domain.usecases

import go.skillbox.domain.exceptions.NetworkDownloadException
import go.skillbox.domain.models.entities.MarsPhoto
import go.skillbox.domain.models.params.GetPhotosByPageParam
import go.skillbox.domain.repository.MarsPhotosRepository
import javax.inject.Inject

class GetPhotosByPageUseCase @Inject constructor() {

    @Inject
    lateinit var repository: MarsPhotosRepository

    suspend fun execute(param: GetPhotosByPageParam): List<MarsPhoto> {
        try {
            return repository.getPhotosByPage(param).photos
        } catch (exception: Exception) {
            throw NetworkDownloadException("Error download")
        }
    }
}