package abhishek.pathak.firebasedemob44

import abhishek.pathak.firebasedemob44.authentication.LoginScreen
import abhishek.pathak.firebasedemob44.firestore.NotesScreen
import abhishek.pathak.firebasedemob44.remote_config.FeatureScreen
import abhishek.pathak.firebasedemob44.realtime_database.ChatScreenRealtimeUI
import abhishek.pathak.firebasedemob44.realtime_database.NotesScreenRealtime
import abhishek.pathak.firebasedemob44.ui.theme.FirebaseDemoB44Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseDemoB44Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseDemoApp()
                }
            }
        }
    }
}

@Composable
private fun FirebaseDemoApp() {
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }

    if (isLoggedIn) {
        MainFeatureScreen()
    } else {
        LoginScreen(
            onLoginSuccess = {
                isLoggedIn = true
            }
        )
    }
}

@Composable
private fun MainFeatureScreen() {
    val screens = remember {
        listOf(
            BottomBarScreen("Firestore", Icons.Filled.Description) { NotesScreen() },
            BottomBarScreen("Realtime", Icons.Filled.Cloud) { NotesScreenRealtime() },
            BottomBarScreen("Chat", Icons.AutoMirrored.Filled.Chat) { ChatScreenRealtimeUI() },
            BottomBarScreen("Config", Icons.Filled.Settings) { FeatureScreen() }
        )
    }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(text = screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            screens[selectedIndex].content()
        }
    }
}

private class BottomBarScreen(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)
