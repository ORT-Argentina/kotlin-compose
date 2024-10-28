package abhishek.pathak.firebasedemob44.realtime_database

import abhishek.pathak.firebasedemob44.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun ChatScreenRealtimeUIPrev() {

    ChatScreenRealtimeUI()
}

@Composable
fun ChatScreenRealtimeUI() {

    val chatViewModelRealtime: ChatViewModelRealtime = viewModel()

    Column{
        ChatsUI(chatViewModelRealtime)
        AddChat(chatViewModelRealtime)
    }

}

@Composable
fun ChatsUI(chatViewModelRealtime: ChatViewModelRealtime) {
    val allChats = chatViewModelRealtime.chats.observeAsState()
    allChats.value?.let { chats ->
        Box(modifier = Modifier.fillMaxWidth().height(600.dp)) {
            LazyColumn {
                items(chats) { chat ->
                    if (chat.userType == 1) {
                        Box(modifier = Modifier.fillMaxWidth().align(Alignment.TopEnd), contentAlignment = Alignment.TopEnd) {
                            Text(
                                text = chat.message, modifier = Modifier
                                    .wrapContentWidth()
                                    .background(Color.Yellow),
                                textAlign = TextAlign.End,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)) {
                            Text(
                                text = chat.message,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .background(Color.Blue),
                                textAlign = TextAlign.Start,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AddChat(chatViewModelRealtime: ChatViewModelRealtime) {
    var newCharContent by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Magenta)
    ) {
        IconButton(
            onClick = {
                chatViewModelRealtime.addChats(Chat(2, newCharContent))
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = "Receiver Icon"
            )
        }
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            value = newCharContent,
            onValueChange = { newCharContent = it },
            label = { Text(text = "message") }
        )

        IconButton(
            onClick = {
                chatViewModelRealtime.addChats(Chat(1, newCharContent))
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Send Icon"
            )
        }
    }

}
