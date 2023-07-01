package pe.edu.cibertec.personalfinance.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry

@Database(entities = [Category::class, Entry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): CategoryDao
    abstract fun daoEntry(): EntryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "personal_finance.db"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE as AppDatabase
        }
    }
}