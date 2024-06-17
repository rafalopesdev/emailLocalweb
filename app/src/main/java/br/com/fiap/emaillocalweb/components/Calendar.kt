import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*


@Composable
fun CalendarView(currentMonth: YearMonth) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7 // Adjust for 0-based indexing

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold ,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        DaysOfWeekHeader()
        DaysGrid(daysInMonth, firstDayOfMonth)
    }
}

@Composable
fun DaysOfWeekHeader() {
    val daysOfWeek = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun DaysGrid(daysInMonth: Int, firstDayOfMonth: Int) {
    val totalCells = (firstDayOfMonth + daysInMonth + 6) / 7 * 7 // Total cells to fill rows evenly
    val daysList = (1..daysInMonth).toList().toMutableList().apply {
        repeat(firstDayOfMonth) { add(0, 0) } // Add empty cells for the first day offset
        repeat(totalCells - size) { add(0) } // Add empty cells for remaining slots
    }

    LazyColumn {
        itemsIndexed(daysList.chunked(7)) { _, week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                week.forEach { day ->
                    Text(
                        text = if (day == 0) "" else day.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

fun generateMonthsOfYear(): List<YearMonth> {
    val currentYear = YearMonth.now().year
    return (1..12).map { month ->
        YearMonth.of(currentYear, month)
    }
}



