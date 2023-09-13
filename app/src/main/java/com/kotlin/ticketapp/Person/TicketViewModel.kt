package com.kotlin.ticketapp.Person
import android.location.Address
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.ticketapp.data.local.entities.Client
import com.kotlin.ticketapp.domain.TicketDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val clienteDb: TicketDb,
) : ViewModel() {

    var Name by mutableStateOf("")
    var PhoneNumber by mutableStateOf("")
    var CellNumber by mutableStateOf("")
    var Email by mutableStateOf("")
    var Address by mutableStateOf("")
    var Birthdate by mutableStateOf(Date())
    var Occupation by mutableStateOf("")

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    val clientes: StateFlow<List<Client>> = clienteDb.ticketDao().getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun saveCliente() {
        viewModelScope.launch {
            val cliente = Client(
                name = Name,
                phoneNumber = PhoneNumber,
                cellNumber = CellNumber,
                email = Email,
                address = Address,
                birthdate = Birthdate,
                occupation = Occupation

            )
            clienteDb.ticketDao().save(cliente)
            limpiar()
        }
    }

    fun limpiar() {
        Name = ""
        PhoneNumber = ""
        CellNumber = ""
        Email = ""
        Address = ""
        Birthdate = Date()
        Occupation = ""

    }
}