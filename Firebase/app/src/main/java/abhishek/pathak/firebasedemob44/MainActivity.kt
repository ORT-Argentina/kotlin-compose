package abhishek.pathak.firebasedemob44

import abhishek.pathak.firebasedemob44.authentication.UserInfo
import abhishek.pathak.firebasedemob44.authentication.LoginScreen
import abhishek.pathak.firebasedemob44.firestore.NotesScreen
import abhishek.pathak.firebasedemob44.realtime_database.ChatScreenRealtimeUI
import abhishek.pathak.firebasedemob44.realtime_database.NotesScreenRealtime
import abhishek.pathak.firebasedemob44.ui.theme.FirebaseDemoB44Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private lateinit var user: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseDemoB44Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //FeatureScreen()
                    //LoginScreen()
                    NotesScreen()
                    //NotesScreenRealtime()
                    //ChatScreenRealtimeUI()
                }
            }
        }
    }
}
