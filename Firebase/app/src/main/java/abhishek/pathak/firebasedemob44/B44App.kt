package abhishek.pathak.firebasedemob44

import android.app.Application
import com.google.firebase.FirebaseApp

class B44App:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}