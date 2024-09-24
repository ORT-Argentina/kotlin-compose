package com.rickAndMorty.list.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.rickAndMorty.list.feature.presentation.ListPresenter
import com.rickAndMorty.list.feature.presentation.ListPresenterPreview
import com.rickAndMorty.shared.model.Character

@Composable
fun CharactersList(
    presenter: ListPresenter,
    modifier: Modifier = Modifier,
) = LazyColumn(
    modifier = modifier.padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
) {
    val characters = presenter.characters.value

    if(characters == null) {
        repeat(3) {
            item{ LoadingCard() }
        }
    } else {
        items(characters) {
            CharacterCard(it)
        }
    }
}
@Composable
private fun LoadingCard() = Card(
    modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.cardElevation(4.dp),
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LoadingBox()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(Modifier.fillMaxWidth().height(16.dp).background(MaterialTheme.colorScheme.primary))
                Box(Modifier.fillMaxWidth(0.8f).height(16.dp).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}

@Composable
private fun CharacterCard(character: Character) = Card(
    modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.cardElevation(4.dp),
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SubcomposeAsyncImage(
                loading = { LoadingBox() },
                error = { ImageError() },
                model = character.imageUrl,
                contentDescription = null
            )
            Column {
                Text(character.name)
                Text(character.status)
            }
        }
    }
}

@Composable
private fun LoadingBox() = Box(Modifier.size(120.dp).background(MaterialTheme.colorScheme.primary))

@Composable
private fun ImageError() = Box(Modifier.size(120.dp).background(MaterialTheme.colorScheme.error))

@Preview(showBackground = true)
@Composable
fun LoadingCharactersPreview() = CharactersList(ListPresenterPreview())

@Preview(showBackground = true)
@Composable
fun CharactersPreview() = CharactersList(
    ListPresenterPreview(
        characters = listOf(
            Character(
                name = "Pepe",
                status = "Alive",
                imageUrl = "www.google.com",
            ),
            Character(
                name = "Pepe 2",
                status = "dead",
                imageUrl = "www.google.com",
            ),
        )
    )
)
