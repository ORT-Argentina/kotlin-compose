package com.manish.mytask.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.manish.mytask.model.Note
import com.manish.mytask.viewmodel.NoteViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "notes_list") {
        // Screen displaying the list of notes
        composable("notes_list") {
            NotesListScreen(viewModel, navController)
        }

        // Screen for adding a new note
        composable("add_note") {
            AddNoteScreen(viewModel, navController)
        }
    }
}

@Composable
fun NotesListScreen(viewModel: NoteViewModel, navController: NavHostController) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, // Align items horizontally in the center
        verticalArrangement = Arrangement.SpaceBetween // Distribute space between items
    ) {
        if (notes.isEmpty()) {
            // Show text prompt when the list is empty
            Text(
                text = "No notes available. Tap the '+' button to add a note.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally) // Center the text horizontally
            )
        } else {
            // Show the list of notes
            LazyColumn(
                modifier = Modifier.weight(1f) // Occupy remaining vertical space
            ) {
                items(notes) { note ->
                    NoteItem(note, onDelete = { viewModel.deleteNote(it) })
                }
            }
        }

        // Floating action button
        FloatingActionButton(
            onClick = {
                navController.navigate("add_note") // Navigate to AddNoteScreen
            },
            modifier = Modifier
                .padding(bottom = 16.dp) // Add padding for spacing
                .align(Alignment.CenterHorizontally) // Center the button horizontally
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Note")
        }
    }
}

@Composable
fun NoteItem(note: Note, onDelete: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
        ) {
            // Text for the note title
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f) // weight to take up available space
            )

            // Button for deleting the note
            Button(onClick = { onDelete(note) }) {
                Text("Delete")
            }
        }
    }
}