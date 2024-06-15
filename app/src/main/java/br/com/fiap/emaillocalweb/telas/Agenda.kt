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
import br.com.fiap.emaillocalweb.FormAgenda
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
            text = "Calendarios",
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
        FormAgenda()
        NavBar(navController)
    }
}

// Função auxiliar para obter o índice do mês atual
fun getCurrentMonthIndex(): Int {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    return currentMonth
}




