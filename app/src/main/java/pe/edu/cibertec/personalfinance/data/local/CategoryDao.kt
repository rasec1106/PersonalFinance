package pe.edu.cibertec.personalfinance.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pe.edu.cibertec.personalfinance.data.model.Category

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Insert
    fun insertAll(categories: List<Category>)

    @Delete
    fun delete(category: Category)

    @Update
    fun update(category: Category)

    @Query("select * from category")
    fun getAll(): List<Category>
}