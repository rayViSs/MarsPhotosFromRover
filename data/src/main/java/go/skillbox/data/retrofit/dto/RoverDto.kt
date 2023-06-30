package go.skillbox.data.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoverDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "landing_date") val landingDate: String,
    @Json(name = "launch_date") val launchDate: String,
    @Json(name = "status") val status: String

)
