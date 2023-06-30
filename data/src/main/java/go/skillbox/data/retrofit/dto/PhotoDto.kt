package go.skillbox.data.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name = "id") val id: Long,
    @Json(name = "sol") val sol: Int,
    @Json(name = "camera") val camera: CameraDto,
    @Json(name = "img_src") val imgSrc: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: RoverDto
)
