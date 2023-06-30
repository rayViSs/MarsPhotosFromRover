package go.skillbox.data.retrofit

import go.skillbox.data.retrofit.dto.PhotosDto

class MarsPhotosDataSource {

    suspend fun getPhotosByPage(page: Int): PhotosDto? {
        val responseLoader = MarsPhotosRetrofitInstance.MarsPhotosRetrofit
        val response = responseLoader?.getMarsPhotos(page)
        if (response != null && response.isSuccessful) return response.body()
        throw RuntimeException(response?.message())
    }
}