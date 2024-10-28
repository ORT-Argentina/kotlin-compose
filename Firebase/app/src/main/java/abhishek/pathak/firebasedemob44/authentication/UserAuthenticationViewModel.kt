package abhishek.pathak.firebasedemob44.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserAuthenticationViewModel : ViewModel() {

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    private lateinit var firebaseUser: FirebaseUser
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> = _registerStatus

    private val _verifyEmailStatus = MutableLiveData<String>()
    val verifyEmailStatus: LiveData<String> = _verifyEmailStatus

    fun login() {
        firebaseAuth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Success"
                } else {
                    _loginStatus.value = "Login failed"
                }
            }
    }

    fun register() {
        firebaseAuth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _registerStatus.value = "Success"
                    firebaseAuth.currentUser?.let {
                        firebaseUser = it
                        sendVerificationEmailLink()
                    }
                } else {
                    _registerStatus.value = "register failed"
                }
            }
    }

    private fun sendVerificationEmailLink() {
        if (this::firebaseUser.isInitialized) {
            firebaseUser.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _verifyEmailStatus.value = "Verification email sent!"
                } else {
                    _verifyEmailStatus.value = "Verification email failed!"
                }
            }
        }
    }
}