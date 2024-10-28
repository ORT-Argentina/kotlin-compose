package abhishek.pathak.firebasedemob44.realtime_database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class ChatViewModelRealtime : ViewModel(){
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    private val firebaseRealtime = FirebaseDatabase.getInstance()
    private val chatDatabase = firebaseRealtime.getReference("chats")

    init{
        fetchChat()
    }

    private fun fetchChat() {
        chatDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val chat = snapshot.children.mapNotNull { it.getValue<Chat>() }
                _chats.value= chat
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun addChats(chat: Chat){
        val id= chatDatabase.push().key
        id?.let{
            chatDatabase.child(it).setValue(chat)
        }
        fetchChat()
    }
}