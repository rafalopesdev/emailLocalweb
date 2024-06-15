package br.com.fiap.emaillocalweb

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

@Composable
fun FormAgenda() {
    var dataAg by remember {
        mutableStateOf("")
    }
    var horaAg by remember {
        mutableStateOf("")
    }
    var eventoAg by remember {
        mutableStateOf("")
    }
    val listaAgenda = remember { mutableStateListOf<AgendaModel>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){
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
                    onValueChange = {dataAg = it},
                    label = { Text(text = "Data")}
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    value = dataAg,
                    onValueChange = {dataAg = it},
                    label = { Text(text = "Horario")}
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = eventoAg,
                onValueChange = {eventoAg = it},
                label = { Text(text = "Digite o evento")}
            )
            Button(
                onClick = {
                    val novoAgendamento = AgendaModel(dataAg, horaAg, eventoAg)
                    listaAgenda.add(novoAgendamento)
                    dataAg = ""
                    horaAg = ""
                    eventoAg = ""
                },

            ) {
                Text(text = "Agendar")
            }
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
                        Text(text = "Dia: ${agenda.data}", modifier = Modifier.weight(1f))
                        Text(text = "Hora: ${agenda.hora}", modifier = Modifier.weight(1f))
                    }
                    Text(text = "Evento: ${agenda.evento}")
                }
            }
        }
    }
}

