import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.emaillocalweb.AgendaModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun FormAgenda() {
    var dataAg by remember { mutableStateOf("") }
    var horaAg by remember { mutableStateOf("") }
    var eventoAg by remember { mutableStateOf("") }
    val listaAgenda = remember { mutableStateListOf<AgendaModel>() }

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    value = dataAg,
                    onValueChange = { dataAg = it },
                    label = { Text(text = "dia/mês/ano") }
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    value = horaAg,
                    onValueChange = { horaAg = it },
                    label = { Text(text = "HH:mm") }
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = eventoAg,
                onValueChange = { eventoAg = it },
                label = { Text(text = "Evento") }
            )
            Button(
                onClick = {
                    try {
                        val data = LocalDate.parse(dataAg, dateFormatter)
                        val hora = LocalTime.parse(horaAg, timeFormatter)
                        val novoAgendamento = AgendaModel(data = data, hora = hora, evento = eventoAg)
                        listaAgenda.add(novoAgendamento)
                        dataAg = ""
                        horaAg = ""
                        eventoAg = ""
                    } catch (e: DateTimeParseException) {
                        // Handle parsing error
                        println("Erro ao parsear a data ou hora: ${e.message}")
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Agendar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ListaAgenda(listaAgenda)
        }
    }
}

@Composable
fun ListaAgenda(listaAgenda: List<AgendaModel>) {
    LazyColumn {
        items(listaAgenda) { agenda ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = "Dia: ${agenda.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Hora: ${agenda.hora.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Text(text = "Evento: ${agenda.evento}")
                }
            }
        }
    }
}
