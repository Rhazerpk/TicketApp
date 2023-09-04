package com.kotlin.ticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kotlin.ticketapp.domain.TicketDb
import com.kotlin.ticketapp.ui.theme.TicketAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var ticketDb: TicketDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TicketScreen(viewModel: ClienteViewModel = hiltViewModel()) {

//    val clientes by viewModel.clientes.collectAsStateWithLifecycle()
//
//    val snackbarHostState = remember { SnackbarHostState() }
//
//    LaunchedEffect(Unit) {
//        viewModel.isMessageShownFlow.collectLatest {
//            if (it) {
//                snackbarHostState.showSnackbar(
//                    message = "Cliente guardado",
//                    duration = SnackbarDuration.Short
//                )
//            }
//        }
//    }
//
//    Scaffold(
//        snackbarHost = { SnackbarHost(snackbarHostState) },
//        modifier = Modifier
//            .fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Clientes") },
//                actions = {
//                    IconButton(onClick = { viewModel.limpiar() }) {
//                        Icon(
//                            imageVector = Icons.Default.Refresh, contentDescription = "Refresh"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//                .padding(8.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Text(text = "Cliente detalles", style = MaterialTheme.typography.titleMedium)
//
//                OutlinedTextField(
//                    value = viewModel.Name,
//                    onValueChange = { viewModel.Name = it },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "Nombre") },
//                    singleLine = true
//                )
//                val keyboardController = LocalSoftwareKeyboardController.current
//                OutlinedButton(onClick = {
//                    keyboardController?.hide()
//                    if(viewModel.Name != ""){
//                        viewModel.saveCliente()
//                        viewModel.setMessageShown()}
//
//                }, modifier = Modifier.fillMaxWidth())
//                {
//                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
//                    Text(text = "Guardar")
//                }
//            }
//
//            Text(text = "Lista de clientes", style = MaterialTheme.typography.titleMedium)
//            LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                items(clientes){ Cliente ->
//                    Text(text = Cliente.Nombre)
//                }
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun TicketScreenPreview() {
    TicketAppTheme {
        TicketScreen()
    }
}
