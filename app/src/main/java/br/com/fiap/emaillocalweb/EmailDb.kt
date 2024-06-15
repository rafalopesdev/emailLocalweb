package br.com.fiap.emaillocalweb
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [EmailModel::class], version = 2)
abstract class EmailDb : RoomDatabase() {

    abstract fun emailDao(): EmailDao

    companion object {

        private lateinit var instance: EmailDb

        fun getDatabase(context: Context): EmailDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        EmailDb::class.java,
                        "email_database"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
