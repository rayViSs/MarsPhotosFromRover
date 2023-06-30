package go.skillbox.marsroverphotosloaderrecyclerview.models

import java.io.Serializable

data class MarsPhotoUiModel(
    val id: Long,
    val sol: Int,
    val camera: CameraUiModel,
    val imgSrc: String,
    val earthDate: String,
    val rover: RoverUiModel
) : Serializable