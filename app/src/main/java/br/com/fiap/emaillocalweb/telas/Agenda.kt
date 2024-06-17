package br.com.fiap.emaillocalweb.telas

import CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.emaillocalweb.AgendaDao
import br.com.fiap.emaillocalweb.AgendaDb
import br.com.fiap.emaillocalweb.AgendaModel
import com.google.accompanist.pager.*
import generateMonthsOfYear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Agenda(navController: NavController) {
    val context = LocalContext.current
    val agendaDao = remember { AgendaDb.getDatabase(context).agendaDao() }

    val monthsOfYear = generateMonthsOfYear()
    val pagerState = rememberPagerState(initialPage = getCurrentMonthIndex())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 56.dp) // Espaço para o NavBar
    ) {
        Text(
            text = "Calendários",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Box(
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .background(Color(0xFFD20B3D))
        ) {
            HorizontalPager(
                count = monthsOfYear.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                CalendarView(monthsOfYear[page])
            }

        }
        Text(
            text = "Arraste >>",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.Gray
        )

        Text(
            text = "Agendar seu evento",
            fontSize = 24.sp,
        )
        AgendaScreen(agendaDao = agendaDao)
    }
}

@Composable
fun AgendaScreen(agendaDao: AgendaDao) {
    var agendaList by remember { mutableStateOf<List<AgendaModel>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val list = agendaDao.listarAgenda()
            if (list.isNotEmpty()) {
                agendaList = list
            } else {
                println("Agenda list is empty")
            }
        }
    }

    var data by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var evento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = "dd/mm/aaaa")}
            )
            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                label = { Text("Hora") },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "HH:mm")}
            )
        }
        OutlinedTextField(
            value = evento,
            onValueChange = { evento = it },
            label = { Text("Evento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "*Preencha todos os campos",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        val interactionSourceEntrar = remember { MutableInteractionSource() }
        val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value
        val allFieldsFilled = data.isNotEmpty() && hora.isNotEmpty() && evento.isNotEmpty()
        Button(
            onClick = {
                val novoEvento = AgendaModel(data = data, hora = hora, evento = evento)
                coroutineScope.launch {
                    agendaDao.salvar(novoEvento)
                    val updatedList = agendaDao.listarAgenda()
                    agendaList = updatedList
                }
                data = ""
                hora = ""
                evento = ""
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isPressedEntrar) Color(0xFF253645) else Color(0xFFD20B3D),
                contentColor = Color.White
            ),
            enabled = allFieldsFilled,
        ) {
            Text("Agendar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (agendaList.isNotEmpty()) {
            AgendaList(agendaList = agendaList, agendaDao = agendaDao, coroutineScope = coroutineScope, updateAgendaList = { agendaList = it })
        } else {
            Text(text = "Nenhum evento agendado",
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun AgendaList(agendaList: List<AgendaModel>, agendaDao: AgendaDao, coroutineScope: CoroutineScope, updateAgendaList: (List<AgendaModel>) -> Unit) {
    Column {
        for (evento in agendaList) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${evento.data} - ${evento.hora}: ${evento.evento}")
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            agendaDao.excluir(evento)
                            val updatedList = agendaDao.listarAgenda()
                            updateAgendaList(updatedList)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(0.dp)
                            .align(Alignment.Top)
                    )
                }
            }
            Divider()
        }
    }
}

fun getCurrentMonthIndex(): Int {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    return currentMonth
}
