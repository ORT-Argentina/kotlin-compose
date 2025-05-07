package ar.edu.ort.frases.model

import com.google.gson.annotations.SerializedName

class DogApi(
    @SerializedName("image_link") val image_link: String,
    @SerializedName("name") val name: String
) {
}