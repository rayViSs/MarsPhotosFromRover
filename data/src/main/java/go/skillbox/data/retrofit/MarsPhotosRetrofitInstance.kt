package go.skillbox.data.retrofit

import go.skillbox.data.retrofit.dto.PhotosDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object MarsPhotosRetrofitInstance {

    private const val BASE_URL = "https://api.nasa.gov"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val MarsPhotosRetrofit: MarsPhotosNetworkLoader? =
        retrofit.create(MarsPhotosNetworkLoader::class.java)
}

interface MarsPhotosNetworkLoader {

    companion object {
        private const val API_KEY = "5suQIFW2DxmTgetsL1H8dgmSbYPVWmKvD67gjdv9"

    }

    @Headers(
        "Accept: Application/json",
        "Content-type: Application/json"
    )
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=$API_KEY")
    suspend fun getMarsPhotos(@Query(value = "page") pageNumber: Int): Response<PhotosDto>
}

