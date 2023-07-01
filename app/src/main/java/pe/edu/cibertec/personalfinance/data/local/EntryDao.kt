package pe.edu.cibertec.personalfinance.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pe.edu.cibertec.personalfinance.data.model.Entry

@Dao
interface EntryDao {

    @Insert
    fun insert(entry: Entry)

    @Insert
    fun insertAll(entry: List<Entry>)

    @Delete
    fun delete(entry: Entry)

    @Update
    fun update(entry: Entry)

    @Query("SELECT * FROM entry")
    fun getAll(): List<Entry>

}