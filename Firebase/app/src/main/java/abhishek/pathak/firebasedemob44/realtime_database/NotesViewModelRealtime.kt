package abhishek.pathak.firebasedemob44.realtime_database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class NotesViewModelRealtime : ViewModel() {

    private val _notes = MutableLiveData<List<NoteRealtime>>()
    val notes: LiveData<List<NoteRealtime>> = _notes
    private val firebaseRealtime = FirebaseDatabase.getInstance()
    private val notesDatabase = firebaseRealtime.getReference("notes")

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        notesDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val note =
                    snapshot.children.mapNotNull { it.getValue<NoteRealtime>() }
                _notes.value = note
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun addNotes(note: NoteRealtime) {
        val id = notesDatabase.push().key
        id?.let {
            notesDatabase.child(it).setValue(note)
        }
        fetchNotes()
    }
}