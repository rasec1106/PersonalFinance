package pe.edu.cibertec.personalfinance.ui.categories


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.cibertec.personalfinance.R
import pe.edu.cibertec.personalfinance.data.local.AppDatabase
import pe.edu.cibertec.personalfinance.data.model.Category

import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme

@Composable
fun CategoryList(){
    val categories = remember{
        mutableStateOf(listOf<Category>())
    }

    //categories.value = Category.populateWithMockData()
    val context = LocalContext.current

    val insertAll = listOf(
        Category(1,"Comida","FOOD","#ff0000"),
        Category(2,"Salud","HEALTH","#00ff00"),
        Category(3,"Casa","HOME","#0000ff"),
        Category(4,"Ahorros","SAVING","#ff00ff"),
        Category(5,"Transporte","TRANSPORT","#ffff00"),
        Category(6,"Viajes","TRAVEL","#00ffff"),
        Category(7,"Ocio","LEISURE","#f0f0f0")

    )
    val dao = AppDatabase.getInstance(context).categoryDao()
    dao.insertAll(insertAll)

    val categoryDao = AppDatabase.getInstance(context).categoryDao()
    categories.value = categoryDao.getAll()

    val remainder = categories.value.size%4
    LazyColumn(){
        items(categories.value.size/4) {
            Card() {
                val slicedArray = categories.value.slice(it*4..it*4+3)
                Row (){
                    slicedArray.forEach { category->
                        Column(modifier = Modifier.weight(4f).padding(8.dp)) {
                            Row{
                                IconButton(modifier = Modifier
                                    .background(
                                        shape = CircleShape,
                                        color = category.getCategoryColor())
                                    , onClick = {}) {
                                    Icon(
                                        ImageVector.vectorResource(id = category.getCategoryIcon()),
                                        contentDescription = category.title
                                    )
                                }
                            }
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(text = category.title)
                            }
                        }
                    }
                }
            }
        }
        if(remainder != 0){
            item{
                Card() {
                    val slicedArray = categories.value.takeLast(remainder)
                    Row (){
                        slicedArray.forEach { category->
                            Column(modifier = Modifier.weight(4f).padding(8.dp)) {
                                Row{
                                    IconButton(modifier = Modifier
                                        .background(
                                            shape = CircleShape,
                                            color = category.getCategoryColor())
                                        , onClick = {}) {
                                        Icon(
                                            ImageVector.vectorResource(id = category.getCategoryIcon()),
                                            contentDescription = category.title
                                        )
                                    }
                                }
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Text(text = category.title)
                                }
                            }
                        }
                        repeat(4-remainder){
                            Box(modifier = Modifier.weight(4f)){}
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListDefaultPreview(){
    PersonalFinanceTheme {
        CategoryList()
    }
}