package go.skillbox.marsroverphotosloaderrecyclerview.models

import java.io.Serializable

data class RoverUiModel(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
) : Serializable