package br.com.fiap.emaillocalweb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AgendaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(agendaModel: AgendaModel): Long

    @Delete
    fun excluir(agendaModel: AgendaModel)

    @Query("DELETE FROM tabela_agenda WHERE id = :id")
    fun excluirById(id: Int)

    @Query("SELECT * FROM tabela_agenda")
    fun listarAgenda(): List<AgendaModel>
}

