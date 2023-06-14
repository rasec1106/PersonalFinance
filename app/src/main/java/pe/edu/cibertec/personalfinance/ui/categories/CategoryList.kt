package pe.edu.cibertec.personalfinance.ui.categories


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.cibertec.personalfinance.R
import pe.edu.cibertec.personalfinance.data.model.Category

import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme

@Composable
fun CategoryList(){
    val categories = remember{
        mutableStateOf(listOf<Category>())
    }
    categories.value = Category.populateWithMockData()
    val remainder = categories.value.size%4
    LazyColumn(){
        items(categories.value.size/4) {
            Card() {
                val slicedArray = categories.value.slice(it*4..it*4+3)
                Row (){
                    slicedArray.forEach { category->
                        IconButton(modifier = Modifier
                            .background(shape = CircleShape, color = category.getCategoryColor())
                            .weight(4f)
                            .padding(8.dp),
                            onClick = {}) {
                            val icon = Icon(
                                ImageVector.vectorResource(id = category.getCategoryIcon()),
                                contentDescription = category.title
                            )
                            Text(text = category.title)
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
                            IconButton(modifier = Modifier
                                .background(shape = CircleShape, color = category.getCategoryColor())
                                .weight(4f)
                                .padding(8.dp),
                                onClick = {}) {
                                val icon = Icon(
                                    ImageVector.vectorResource(id = category.getCategoryIcon()),
                                    contentDescription = category.title
                                )
                                Text(text = category.title)
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
fun DefaultPreview(){
    PersonalFinanceTheme {
        CategoryList()
    }
}