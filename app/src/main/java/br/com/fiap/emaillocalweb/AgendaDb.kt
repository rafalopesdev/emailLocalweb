package br.com.fiap.emaillocalweb
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AgendaModel::class], version = 1)
abstract class AgendaDb : RoomDatabase() {

    abstract fun eventoDao(): AgendaDao

    companion object {
        private lateinit var instance: AgendaDb

        fun getDatabase(context: Context): AgendaDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        AgendaDb::class.java,
                        "agenda_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
