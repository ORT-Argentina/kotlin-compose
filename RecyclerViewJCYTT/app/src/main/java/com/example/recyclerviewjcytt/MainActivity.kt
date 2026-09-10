package com.example.recyclerviewjcytt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyclerviewjcytt.ui.theme.RecyclerViewJCYTTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerViewJCYTTTheme {
              RecyclerView()
            }
        }
    }
}

@Composable
fun ListItem(name : String){

    val expanded = remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        if (expanded.value) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){

        Column(modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()) {

            Row{
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Course")
                    Text(text = name, style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))
                }
                OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }

            if (expanded.value){

                Column(modifier = Modifier.padding(
                    top = extraPadding.coerceAtLeast(0.dp),
                    bottom = extraPadding.coerceAtLeast(0.dp),
                )) {
                   Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                }

            }
            }

        }



    }



@Composable
fun RecyclerView(names : List<String> = List(90){"$it"}){

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names){ name ->
            ListItem(name = name)
        }

    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecyclerViewJCYTTTheme {
        RecyclerView()
    }
}