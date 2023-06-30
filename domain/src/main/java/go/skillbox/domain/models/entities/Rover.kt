package go.skillbox.domain.models.entities

data class Rover(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
)