import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.ChecklistGroup

// --- 1. Paleta de Colores Relajante ---
val PastelBlue = Color(0xFFB3E5FC) // Azul pastel para elementos principales
val SoftBlue = Color(0xFF81D4FA)   // Un azul un poco más oscuro para contraste
val SoftGray = Color(0xFFF5F5F5)   // Gris suave para el fondo
val DarkGrayText = Color(0xFF424242) // Gris oscuro para texto principal
val LightGrayText = Color(0xFF757575) // Gris claro para texto secundario

// Colores pastel para los grupos de checklists
val PastelRed = Color(0xFFFFCDD2)
val PastelGreen = Color(0xFFC8E6C9)
val PastelYellow = Color(0xFFFFF9C4)
val PastelPurple = Color(0xFFE1BEE7)


// --- 3. Tema de la Aplicación (Wrapper) ---
@Composable
fun ZenListTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = PastelBlue,
            secondary = SoftBlue,
            background = SoftGray,
            surface = Color.White, // Color para las tarjetas
            onPrimary = DarkGrayText,
            onSecondary = DarkGrayText,
            onBackground = DarkGrayText,
            onSurface = DarkGrayText
        ),
        typography = MaterialTheme.typography,
        content = content
    )
}

// --- 4. Pantalla Principal ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZenListHomeScreen(groups: List<ChecklistGroup>) {
    Scaffold(
        containerColor = SoftGray, // Color de fondo general
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ZenList",
                        fontWeight = FontWeight.Bold,
                        color = DarkGrayText
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White // Un fondo limpio para la barra superior
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción para crear nuevo grupo */ },
                containerColor = PastelBlue,
                contentColor = DarkGrayText,
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir grupo de checklist")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre tarjetas
        ) {
            items(groups) { group ->
                GroupCard(group = group)
            }
        }
    }
}

// --- 5. Componente para cada Tarjeta de Grupo ---
@Composable
fun GroupCard(group: ChecklistGroup) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de color a la izquierda
            Box(
                modifier = Modifier
                    .size(8.dp, 50.dp) // Barra vertical
                    .clip(RoundedCornerShape(4.dp))
                    .background(group.color)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Título y contador de tareas
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = group.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkGrayText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${group.completedTasks} de ${group.totalTasks} tareas",
                    fontSize = 14.sp,
                    color = LightGrayText
                )
            }
        }
    }
}


// --- 6. Vista Previa para Android Studio ---
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ZenListHomeScreenPreview() {
    // Datos de ejemplo para la vista previa
    val sampleGroups = listOf(
        ChecklistGroup(1, "Trabajo", PastelBlue, 3, 5),
        ChecklistGroup(2, "Personal", PastelGreen, 1, 8),
        ChecklistGroup(3, "Supermercado", PastelYellow, 10, 10),
        ChecklistGroup(4, "Gimnasio", PastelRed, 0, 4),
        ChecklistGroup(5, "Proyecto de Fin de Semana", PastelPurple, 1, 2)
    )

    ZenListTheme {
        ZenListHomeScreen(groups = sampleGroups)
    }
}

// Puedes ignorar esta clase. Es solo para que el código sea ejecutable como una app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sampleGroups = listOf(
                ChecklistGroup(1, "Trabajo", PastelBlue, 3, 5),
                ChecklistGroup(2, "Personal", PastelGreen, 1, 8),
                ChecklistGroup(3, "Supermercado", PastelYellow, 10, 10),
                ChecklistGroup(4, "Gimnasio", PastelRed, 0, 4),
                ChecklistGroup(5, "Proyecto de Fin de Semana", PastelPurple, 1, 2)
            )
            ZenListTheme {
                ZenListHomeScreen(groups = sampleGroups)
            }
        }
    }
}