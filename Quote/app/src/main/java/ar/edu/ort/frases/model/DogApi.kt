package ar.edu.ort.frases.model

import com.google.gson.annotations.SerializedName

data class DogApi(
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("name") val name: String
) {
}