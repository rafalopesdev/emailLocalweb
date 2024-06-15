package br.com.fiap.emaillocalweb.telas

import CalendarView
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.emaillocalweb.components.NavBar
import generateMonthsOfYear
import java.util.*
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Agenda(navController: NavController) {
    val monthsOfYear = generateMonthsOfYear()
    val pagerState = rememberPagerState(initialPage = getCurrentMonthIndex())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calendario",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .height(300.dp)
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
            text = "Outro conteúdo abaixo do pager",
            modifier = Modifier.padding(top = 16.dp)
        )
        NavBar(navController)
    }
}

// Função auxiliar para obter o índice do mês atual
fun getCurrentMonthIndex(): Int {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    return currentMonth
}


//@Composable
//fun Agenda (navController: NavController) {
//    var data by remember { mutableStateOf("") }
//    var horario by remember { mutableStateOf("") }
//    var evento by remember { mutableStateOf("") }
//    var eventoAgendado by remember { mutableStateOf("") }
//    val interactionSourceEntrar = remember { MutableInteractionSource() }
//    val isPressedEntrar = interactionSourceEntrar.collectIsPressedAsState().value
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
//        contentAlignment = Alignment.Center
//    ) {
//
//        Column {
//
//            Row (verticalAlignment = Alignment.CenterVertically){
//                Image(painter = painterResource(id = R.drawable.logolocaw),
//                    contentDescription = "imagem logo",
//                    modifier = Modifier
//                        .size(60.dp)
//                        .padding(start = 10.dp)
//                )
//                Text(
//                    text = "Agende seu Evento",
//                    modifier = Modifier.fillMaxWidth(),
//                    fontSize = 25.sp,
//                    color = Color(0xFF253645),
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                )}
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                //elevation = 4.dp,
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF253645)),
//
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//
//                    Text(
//                        text = "Dados do Evento",
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                    OutlinedTextField(
//                        value = data,
//                        onValueChange = { data = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp),
//
//                        placeholder = {
//                            Text(text = "Data do evento",
//                                color = Color.White
//                            )
//                        },
//                        label = {
//                            Text(text = "Data",
//                                color = Color.White
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Text
//                        ),
//                        textStyle = LocalTextStyle.current.copy(color = Color.White),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFFD20B3D),
//                            unfocusedBorderColor = Color.White,
//                            cursorColor = Color.White
//                        )
//
//                    )
//                    OutlinedTextField(
//                        value = horario,
//                        onValueChange = { horario = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp),
//                        placeholder = {
//                            Text(text = "Horário do evento",
//                                color = Color.White)
//                        },
//                        label = {
//                            Text(text = "Horário",
//                                color = Color.White)
//                        },
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Text
//                        ),
//                        textStyle = LocalTextStyle.current.copy(color = Color.White),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFFD20B3D),
//                            unfocusedBorderColor = Color.White,
//                            cursorColor = Color.White
//                        )
//                    )
//                    OutlinedTextField(
//                        value = evento,
//                        onValueChange = { evento = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp),
//                        placeholder = {
//                            Text(text = "Descrição do evento",
//                                color = Color.White)
//                        },
//                        label = {
//                            Text(text = "Evento",
//                                color = Color.White)
//                        },
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Text
//                        ),
//                        textStyle = LocalTextStyle.current.copy(color = Color.White),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFFD20B3D),
//                            unfocusedBorderColor = Color.White,
//                            cursorColor = Color.White
//                        )
//                    )
//                    Button(
//                        onClick = {
//                            eventoAgendado = "$evento agendado no dia $data às $horario"
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 32.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = if (isPressedEntrar) Color(0xFF253645) else Color(0xFFD20B3D),
//                            contentColor = Color.White
//                        ),
//                    ) {
//                        Text(text = "AGENDAR")
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFD20B3D)
//                ),
//            ) {
//                Column(modifier = Modifier.padding(10.dp)) {
//                    Text(
//                        text = "Evento agendado",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = eventoAgendado,
//                        fontSize = 16.sp,
//                        color = Color.White
//                    )
//                }
//            }
//            NavBar(navController)
//        }
//    }
//}

