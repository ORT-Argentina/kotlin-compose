package ar.edu.ort.myfirstandroidapp.data

import ar.edu.ort.myfirstandroidapp.R
import androidx.compose.runtime.Immutable

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.avatar else R.drawable._28284_avatar_male_man_mature_old_icon
)