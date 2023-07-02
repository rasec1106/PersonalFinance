package pe.edu.cibertec.personalfinance.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import pe.edu.cibertec.personalfinance.R


@Entity(tableName = "category")
data class Category (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCategory")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "color")
    val color: String
){
    fun getCategoryIcon(): Int{
        val map = mapOf(
            "FOOD" to R.drawable.lunch_dinning_foreground,
            "HEALTH" to R.drawable.health_foreground,
            "HOME" to R.drawable.home_foreground,
            "SAVING" to R.drawable.saving_foreground,
            "TRANSPORT" to R.drawable.transportation_foreground,
            "TRAVEL" to R.drawable.travel_foreground,
            "LEISURE" to R.drawable.leisure_foreground
        )
        return map.getValue(icon)
    }

    fun getCategoryColor(): Color{
        return Color(android.graphics.Color.parseColor(color))
    }

    companion object{
        fun populateWithMockData(): List<Category>{
            return listOf(
                Category(1,"Comida","FOOD","#ff0000"),
                Category(2,"Salud","HEALTH","#00ff00"),
                Category(3,"Casa","HOME","#0000ff"),
                Category(4,"Ahorros","SAVING","#ff00ff"),
                Category(5,"Transporte","TRANSPORT","#ffff00"),
                Category(6,"Viajes","TRAVEL","#00ffff"),
                Category(7,"Ocio","LEISURE","#f0f0f0")
            )
        }
    }
}