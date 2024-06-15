package br.com.fiap.emaillocalweb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "tabela_email")
data class EmailModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val email: String,
    val assunto: String,
    val message: String,
    val horario: String? = getCurrentTime()
)

fun getCurrentTime(): String? {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return current.format(formatter)
}
