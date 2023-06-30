package go.skillbox.marsroverphotosloaderrecyclerview.models

import java.io.Serializable

data class CameraUiModel(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String
) : Serializable