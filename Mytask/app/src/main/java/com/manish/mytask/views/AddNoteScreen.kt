package com.manish.mytask.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manish.mytask.data.local.NoteDao
import com.manish.mytask.model.Note
import com.manish.mytask.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun AddNoteScreen(viewModel: NoteViewModel, navController: NavHostController) {
    var title by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(modifier = Modifier.fillMaxWidth(), value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            if (title.isNotBlank()) {
                viewModel.addNote(Note(title = title, category = "general"))
                navController.popBackStack()
            }
        }) {
            Text("Save Note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNoteScreenPreview() {
    // Mock NoteDao for preview
    val mockNoteDao = object : NoteDao {
        override fun getAllNotes(): Flow<List<Note>> = flowOf(emptyList())

        override suspend fun insert(note: Note) {
            println("Mock insert: ${note.title}")
        }

        override suspend fun update(note: Note) {
            TODO("Not yet implemented")
        }

        override suspend fun delete(note: Note) {
            println("Mock delete: ${note.title}")
        }
    }

    // Create a dummy ViewModel with the mock NoteDao
    val dummyViewModel = NoteViewModel(mockNoteDao)

    // Use a remember NavController for preview
    val dummyNavController = rememberNavController()

    AddNoteScreen(viewModel = dummyViewModel, navController = dummyNavController)
}
