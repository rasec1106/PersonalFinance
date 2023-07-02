package pe.edu.cibertec.personalfinance.ui.entries

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.Login.Models.FondoImage
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.data.repository.EntryRepository
import pe.edu.cibertec.personalfinance.ui.Route
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import pe.edu.cibertec.personalfinance.util.Result

@Composable
fun EntryList(navController: NavController){
    FondoImage()
    val entries = remember{
        mutableStateOf(listOf<Entry>())
    }
    //entries.value = Entry.populateWithMockData()

    val context = LocalContext.current
    var str = remember {
        mutableStateOf("")
    }
    val entryRepository = EntryRepository()
    entryRepository.getEntries(1,context) { result ->
        if(result is Result.Success){
            entries.value = result.data!!

        } else{
            str.value = result.message.toString()
        }
    }

    LazyColumn(){
        item{
            Button(modifier = Modifier
                .padding(10.dp, 15.dp, 10.dp, 15.dp)
                .fillMaxWidth()
                .height(48.dp),
                colors=ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xAB0389FF),
                    disabledBackgroundColor = Color(0xFF586DF7),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                    disabledContentColor = androidx.compose.ui.graphics.Color.White),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "entry",
                    value = Entry(0,0.0, Category(1,"Comida","FOOD","#ff0000"),"","",0)
                )
                navController.navigate(Route.EntryDetail.route)
            }) {
                Text(text = "Nueva Entrada")
            }
        }
        items(entries.value) {entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                backgroundColor = Color.hsl(207F, 0.22F, 0.88F,1F),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        ) {
                        Text(text = entry.date,
                            modifier = Modifier
                                .padding(20.dp,2.dp,20.dp,2.dp),

                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                        Column(
                            modifier = Modifier
                                .weight(4f)
                                .padding(2.dp)
                        ) {
                            IconButton(modifier = Modifier
                                .padding(6.dp,6.dp)
                                .size(55.dp, 45.dp)
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
                            Spacer(modifier=Modifier.padding(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),

                                horizontalArrangement = Arrangement.Start,

                            ) {
                                Box(
                                modifier= Modifier
                                    .background(
                                        color = Color(0xAB32C7B0),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        1.dp,
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0xAB32C7B0)
                                    )
                                    .padding(12.dp, 2.dp, 12.dp, 2.dp)



                                ){
                                    Text(text = entry.category.title, fontSize = 16.sp,)
                                }

                            }
                            Spacer(modifier=Modifier.padding(6.dp))
                            Row(

                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,

                            ) {
                                Text(text = entry.comment,
                                    fontSize = 13.sp,
                                    fontStyle = FontStyle.Italic)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(2.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,

                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = "S/${entry.amount}", fontSize = 16.sp)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(6f)
                                .padding(1.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row {
                                Column {
                                    IconButton(modifier = Modifier.weight(1f), onClick = {
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            key = "entry",
                                            value = entry
                                        )
                                        navController.navigate(Route.EntryDetail.route)
                                    }) {
                                        Icon(Icons.Filled.Edit,null)
                                    }
                                }
                                Column {
                                    IconButton(modifier = Modifier.weight(1f), onClick = {
                                        entryRepository.deleteEntry(1, context, entry.id){ result ->
                                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                                            entryRepository.getEntries(1,context) {result ->
                                                if(result is Result.Success){
                                                    entries.value = result.data!!

                                                } else{
                                                    str.value = result.message.toString()
                                                }
                                            }
                                        }
                                    }) {
                                        Icon(Icons.Filled.Delete, null)
                                    }
                                }
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
fun EntryListDefaultPreview(){
    PersonalFinanceTheme {
        EntryList(navController = rememberNavController())
    }
}