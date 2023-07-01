package pe.edu.cibertec.personalfinance.ui.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.cibertec.personalfinance.data.local.AppDatabase
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme

@Composable
fun EntryList(){
    val entries = remember{
        mutableStateOf(listOf<Entry>())
    }
    //entries.value = Entry.populateWithMockData()

    val context = LocalContext.current

    val insertAll = listOf(
        Entry(1, 50.5,Category(1,"Comida","FOOD","#ff0000"),"2023-06-01", "Norky's", 0),
        Entry(2, 10.5,Category(2,"Salud","HEALTH","#00ff00"),"2023-06-01", "Medico a domicilio", 0),
        Entry(3, 20.5,Category(3,"Casa","HOME","#0000ff"),"2023-06-08", "Pasta dental y cepillo", 0),
        Entry(4, 150.0,Category(4,"Ahorros","SAVING","#ff00ff"),"2023-06-09", "Ahorro viaje", 1),
        Entry(5, 15.0,Category(5,"Transporte","TRANSPORT","#ffff00"),"2023-06-11", "Pasajes universidad", 0),
        Entry(6, 350.80,Category(6,"Viajes","TRAVEL","#00ffff"),"2023-06-12", "Viaje a Trujillo", 0),
        Entry(7, 20.0,Category(1,"Comida","FOOD","#ff0000"),"2023-06-13", "Salchipapa", 0),
        Entry(8, 38.0,Category(7,"Ocio","LEISURE","#f0f0f0"),"2023-06-15", "Cineplanet", 0)
    )
    val dao = AppDatabase.getInstance(context).daoEntry()
    dao.insertAll(insertAll)

    val entryDao = AppDatabase.getInstance(context).daoEntry()
    entries.value = entryDao.getAll()


    LazyColumn(){
        items(entries.value) {entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                backgroundColor = Color.hsl(207F, 0.22F, 0.88F,1F)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = entry.date, modifier = Modifier.padding(2.dp), fontSize = 13.sp)
                    }
                    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                        Column(
                            modifier = Modifier
                                .weight(4f)
                                .padding(2.dp)
                        ) {
                            IconButton(modifier = Modifier
                                .size(50.dp)
                                .background(
                                    shape = CircleShape,
                                    color = entry.category.getCategoryColor()
                                ), onClick = {}) {
                                Icon(
                                    ImageVector.vectorResource(id = entry.category.getCategoryIcon()),
                                    contentDescription = entry.category.title
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(15f)
                                .padding(2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(text = entry.category.title, fontSize = 16.sp)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(text = entry.comment, fontSize = 13.sp, fontStyle = FontStyle.Italic)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(2.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = "S/${entry.amount}", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    PersonalFinanceTheme {
        EntryList()
    }
}