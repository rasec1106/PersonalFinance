package pe.edu.cibertec.personalfinance.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "entry")
@Parcelize
data class Entry (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idEntry")
    val id: Int,

    @ColumnInfo(name = "amount")
    @NonNull
    val amount: Double,

    @Embedded
    val category: Category,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "type")
    val type: Int
): Parcelable {

    companion object{
        fun populateWithMockData(): List<Entry>{
            return listOf(
                Entry(1, 50.5,Category(1,"Comida","FOOD","#ff0000"),"2023-06-01", "Norky's", 0),
                Entry(2, 10.5,Category(2,"Salud","HEALTH","#00ff00"),"2023-06-01", "Medico a domicilio", 0),
                Entry(3, 20.5,Category(3,"Casa","HOME","#0000ff"),"2023-06-08", "Pasta dental y cepillo", 0),
                Entry(4, 150.0,Category(4,"Ahorros","SAVING","#ff00ff"),"2023-06-09", "Ahorro viaje", 1),
                Entry(5, 15.0,Category(5,"Transporte","TRANSPORT","#ffff00"),"2023-06-11", "Pasajes universidad", 0),
                Entry(6, 350.80,Category(6,"Viajes","TRAVEL","#00ffff"),"2023-06-12", "Viaje a Trujillo", 0),
                Entry(7, 20.0,Category(1,"Comida","FOOD","#ff0000"),"2023-06-13", "Salchipapa", 0),
                Entry(8, 38.0,Category(7,"Ocio","LEISURE","#f0f0f0"),"2023-06-15", "Cineplanet", 0)
            )
        }
    }
}

