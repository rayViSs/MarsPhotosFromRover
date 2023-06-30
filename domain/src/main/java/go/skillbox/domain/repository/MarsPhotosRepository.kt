package go.skillbox.domain.repository

import go.skillbox.domain.models.entities.MarsPhotosList
import go.skillbox.domain.models.params.GetPhotosByPageParam

interface MarsPhotosRepository {

    suspend fun getPhotosByPage(params: GetPhotosByPageParam): MarsPhotosList
}