package ar.edu.ort.bootsshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val drawerState = rememberDrawerState(initialValue = Closed)
            val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()

            if (drawerOpen) {
                LaunchedEffect(Unit) {
                    try {
                        drawerState.open()
                    } finally {
                        viewModel.resetOpenDrawerAction()
                    }
                }
            }

            val scope = rememberCoroutineScope()
            if (drawerState.isOpen) {
                BackHandler {
                    scope.launch {
                        viewModel.resetOpenDrawerAction()
                    }
                }
            }

            AppBootShop(viewModel, drawerState)
        }
    }
}
