package br.com.fiap.emaillocalweb.telas

import CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.emaillocalweb.AgendaDao
import br.com.fiap.emaillocalweb.AgendaDb
import br.com.fiap.emaillocalweb.AgendaModel
import com.google.accompanist.pager.*
import generateMonthsOfYear
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
            modifier = Modifier.padding(bottom = 16.dp)
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
            text = "Agende aqui",
            modifier = Modifier.padding(top = 16.dp)
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
            try {
                val list = agendaDao.listarAgenda()
                if (list.isNotEmpty()) {
                    agendaList = list
                } else {
                    println("Agenda list is empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var data by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var evento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Data") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = evento,
            onValueChange = { evento = it },
            label = { Text("Evento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val novoEvento = AgendaModel(data = data, hora = hora, evento = evento)
                coroutineScope.launch {
                    try {
                        agendaDao.salvar(novoEvento)
                        val updatedList = agendaDao.listarAgenda()
                        if (updatedList.isNotEmpty()) {
                            agendaList = updatedList
                        } else {
                            println("Updated agenda list is empty")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                data = ""
                hora = ""
                evento = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agendar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (agendaList.isNotEmpty()) {
            AgendaList(agendaList = agendaList)
        } else {
            Text(text = "Nenhum evento agendado", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun AgendaList(agendaList: List<AgendaModel>) {
    Column {
        for (evento in agendaList) {
            Text(
                text = "${evento.data} - ${evento.hora}: ${evento.evento}",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

fun getCurrentMonthIndex(): Int {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    return currentMonth
}
