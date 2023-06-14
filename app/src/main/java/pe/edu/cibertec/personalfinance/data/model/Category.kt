package pe.edu.cibertec.personalfinance.data.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import pe.edu.cibertec.personalfinance.R
import java.io.IOException

data class Category (
    val id: Int,
    val title: String,
    val icon: String,
    val color: String
){
    private val map = mapOf(
        "FOOD" to R.drawable.lunch_dinning_foreground,
        "HEALTH" to R.drawable.health_foreground,
        "HOME" to R.drawable.home_foreground,
        "SAVING" to R.drawable.saving_foreground,
        "TRANSPORT" to R.drawable.transportation_foreground,
        "TRAVEL" to R.drawable.travel_foreground,
        "LEISURE" to R.drawable.leisure_foreground
    )

    fun getCategoryIcon(): Int{
        return map.getValue(icon)
    }

    fun getCategoryColor(): Color{
        return Color(android.graphics.Color.parseColor(color))
    }

    companion object{
        fun populateWithMockData(): List<Category>{
            return listOf(
                Category(1,"Comida","FOOD","#ff0000"),
                Category(2,"Salud","HEALTH","#ff0000"),
                Category(3,"Casa","HOME","#ff0000"),
                Category(4,"Ahorros","SAVING","#ff0000"),
                Category(5,"Transporte","TRANSPORT","#ff0000"),
                Category(4,"Viajes","TRAVEL","#ff0000"),
                Category(5,"Ocio","LEISURE","#ff0000")
            )
        }
    }
}