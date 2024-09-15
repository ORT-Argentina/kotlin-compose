package ar.edu.ort.myfirstandroidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.myfirstandroidapp.data.Message
import ar.edu.ort.myfirstandroidapp.ui.theme.FirstAndroidProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("MiApp", "onCreate")
        setContent {
            FirstAndroidProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val messageList = listOf(
        Message("Martin", "¿Hola Como estas?", ""),
        Message("Martin", "¿Todo bien?", ""),
        Message("Martin", "¿Estan haciendo el challenge?", ""),
        Message("Martin", "¿Hola Como estas?", ""),
        Message("Martin", "¿Todo bien?", ""),
        Message("Martin", "¿Estan haciendo el challenge?", ""),
        Message("Martin", "¿Hola Como estas?", ""),
        Message("Martin", "¿Todo bien?", ""),
    )



    Surface( color = Color.Cyan, modifier = Modifier.fillMaxSize()) {
        Column() {

            MessageList(messages = messageList)

            val state = rememberLazyListState()

            LaunchedEffect(key1 = Unit) {
                state.animateScrollToItem(index = 5)
            }

            LazyColumn (Modifier.fillMaxSize(), state) {
                items(messageList) { message ->
                    ChatItemBubble(
                        message = message,
                        isUserMe = true,
                        authorClicked = { }
                    )
                }
            }

            /*LazyColumn (
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {

              item {
                  Text(text = "Primero")
              }


              items(5) { index ->
                  Text(text = "Item Nro.: $index")
              }

              item {
                  Text(text = "Último")
              }
          }*/
        }

    }
}

@Composable
fun MessageList(messages: List<Message>) {
    Column {
        messages.forEach { message ->
            ChatItemBubble(message, true, {})
        }
    }
}


@Composable
fun ChatItemBubble(
    message: Message,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {

    val backgroundBubbleColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            ClickableMessage(
                message = message,
                isUserMe = isUserMe
            )
        }

        message.image?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = backgroundBubbleColor,
                shape = ChatBubbleShape
            ) {
                Image(
                    painter = painterResource(it),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(160.dp),
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
        }
    }
}

@Composable
fun Avatar(name: String, modifier: Modifier = Modifier) {
    Row() {
        Image(
            painterResource(R.drawable.avatar),
            contentDescription = "avatar",
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = "$name!",
            modifier = modifier
        )
    }

}

@Composable
fun ClickableMessage(
    message: Message,
    isUserMe: Boolean
) {
    val boldText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(message.author)
        }
        append("\n")
        append(message.content)
    }

    //Avatar(name = message.author)
    ClickableText(
        text = boldText,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        modifier = Modifier.padding(16.dp),
        onClick = {
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAndroidProjectTheme {
        Greeting("Martin")
    }
}

