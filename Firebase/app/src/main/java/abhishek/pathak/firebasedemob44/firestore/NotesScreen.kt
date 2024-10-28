package abhishek.pathak.firebasedemob44.firestore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
@Preview
fun NotesScreen() {
    val notesViewModel: NotesViewModel = viewModel()

    Column {
        AddNote(notesViewModel)
        NotesUI(notesViewModel)
    }
}

@Composable
fun NotesUI(notesViewModel: NotesViewModel) {
    val allNotes = notesViewModel.notes.observeAsState()
    allNotes.value?.let { notes ->
        LazyColumn {
            items(notes) { note ->
                Text(
                    text = note.content, modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun AddNote(viewModel: NotesViewModel) {
    var newNoteTitle by remember { mutableStateOf("") }
    var newNoteContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Magenta)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = newNoteTitle,
            onValueChange = { newNoteTitle = it },
            label = { Text(text = "Title") }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = newNoteContent,
            onValueChange = { newNoteContent = it },
            label = { Text(text = "Content") }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {

                viewModel.addNotes(Note(UUID.randomUUID().toString(), newNoteTitle, newNoteContent, "solo"))
            }) {
            Text(text = "Add Note")
        }
    }
}