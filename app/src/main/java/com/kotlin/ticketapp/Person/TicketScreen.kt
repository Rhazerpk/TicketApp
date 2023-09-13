package com.kotlin.ticketapp.Person
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TicketScreen(
    viewModel: ClientViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    var invalidEmail by remember { mutableStateOf(true) }
    var invalidDate by remember { mutableStateOf(true) }
    val clients by viewModel.clientes.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var gotFocusDate by remember { mutableStateOf(false) }
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    var date by remember { mutableStateOf("") }
    @Suppress("DEPRECATION") val datePickerDialog = DatePickerDialog(
        context, { _, year1, month1, day1 ->
            val month2: Int = month1 + 1
            date = "$day1 - $month2 - $year1"
            viewModel.Birthdate = Date(year1, month2, day1)
            invalidDate = dateInvalid(viewModel.Birthdate)
        }, year, month, day
    )

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Cliente guardado",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Clients") },
                actions = {
                    IconButton(
                        onClick = { viewModel.limpiar() }
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Client details", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = viewModel.Name,
                    onValueChange = { viewModel.Name = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = viewModel.PhoneNumber,
                    onValueChange = { viewModel.PhoneNumber = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Telephone") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                OutlinedTextField(
                    value = viewModel.CellNumber,
                    onValueChange = { viewModel.CellNumber = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Cellphone") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                OutlinedTextField(
                    value = viewModel.Email,
                    onValueChange = { viewModel.Email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { invalidEmail = emailInvalid(viewModel.Email) },
                    label = { Text(text = "Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                OutlinedTextField(
                    value = viewModel.Address,
                    onValueChange = { viewModel.Address = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Address") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions =  KeyboardActions(
                        onNext = {
                            focusManager.clearFocus()
                            datePickerDialog.show()
                            gotFocusDate = true
                        }
                    )
                )
                OutlinedTextField(
                    label = { Text(text = "Date Birth")},
                    value = date,
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    datePickerDialog.show()
                                }
                                .size(30.dp, 30.dp)
                        )
                    },
                )

                //Birth date\
                DropdownMenuBox(viewModel)

                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedButton(onClick = {
                    keyboardController?.hide()
                    if (viewModel.Validation()) {
                        viewModel.saveCliente()
                        viewModel.setMessageShown()
                    }

                }, modifier = Modifier.fillMaxWidth())
                {
                    Text(text = "Save")
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Save")

                }
            }

            Text(text = "Client List", style = MaterialTheme.typography.titleMedium)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(clients){ client ->
                    Text(text = client.name)
                    Text(text = client.name)
                }
            }
        }
    }
}

private fun emailInvalid(email: String): Boolean {
    val regex = Regex("[A-Za-z0-9_]{3,}@[A-Za-z0-9]{3,}(\\.[A-Za-z0-9_]{3,})(\\.[A-Za-z0-9_]{2,})*")
    return !regex.matches(email)
}

@Suppress("DEPRECATION")
private fun dateInvalid(fecha: Date): Boolean {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR) - 17
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    return fecha > Date(year, month, day) || fecha < Date(1930,1,1)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(
    viewModel: ClientViewModel
) {

    val opciones = arrayOf("Engineer", "Doctor", "Lawyer", "Architect", "Firefighter")
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)

    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = viewModel.Occupation,
                label = { Text(text = "Select an occupation") },
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                opciones.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            viewModel.Occupation = item
                            expanded = false

                        }
                    )
                }
            }
        }
    }
}





