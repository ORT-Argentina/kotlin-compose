package com.manish.mytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.manish.mytask.data.local.DatabaseProvider
import com.manish.mytask.ui.theme.MyTaskTheme
import com.manish.mytask.viewmodel.NoteViewModel
import com.manish.mytask.views.NoteScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Access the database instance and DAO
        val database = DatabaseProvider.getDatabase(applicationContext)
        val noteDao = database.noteDao()

        // Create the ViewModel
        val viewModel = NoteViewModel(noteDao)

        // Set the content to NoteScreen
        setContent {
            MyTaskTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text("My Task") })
                }, content = { padding ->
                    Column(modifier = Modifier.padding(padding)) {
                        NoteScreen(viewModel = viewModel)
                    }
                })
            }
        }
    }
}
