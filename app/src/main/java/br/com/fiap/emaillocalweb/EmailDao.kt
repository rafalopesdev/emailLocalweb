package br.com.fiap.emaillocalweb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(emailModel: EmailModel): Long

    @Delete
    fun excluir(emailModel: EmailModel): Int

    @Query("SELECT * FROM tabela_email")
    fun listarEmails(): List<EmailModel>

    @Query("SELECT * FROM tabela_email WHERE id = :id")
    fun getEmailById(id: String): EmailModel?

}