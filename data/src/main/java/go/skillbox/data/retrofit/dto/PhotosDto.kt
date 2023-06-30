package go.skillbox.data.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosDto(
    @Json(name = "photos") val photos: List<PhotoDto>
)
