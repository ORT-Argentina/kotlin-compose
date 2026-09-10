package com.example.myapplication

import PastelBlue
import PastelGreen
import PastelPurple
import PastelRed
import PastelYellow
import ZenListHomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.ChecklistGroup
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    val checklistGroups = remember {
                        mutableStateListOf(
                            ChecklistGroup(1, "Trabajo", PastelBlue, 3, 5),
                            ChecklistGroup(2, "Personal", PastelGreen, 1, 8),
                            ChecklistGroup(3, "Supermercado", PastelYellow, 10, 10),
                            ChecklistGroup(4, "Gimnasio", PastelRed, 0, 4),
                            ChecklistGroup(5, "Proyecto de Fin de Semana", PastelPurple, 1, 2)
                        )
                    }

                    ZenListHomeScreen(checklistGroups)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}