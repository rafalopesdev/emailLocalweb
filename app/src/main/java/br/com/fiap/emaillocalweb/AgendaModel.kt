package br.com.fiap.emaillocalweb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "tabela_agenda")
data class AgendaModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: String,
    val hora: String,
    val evento: String
)

