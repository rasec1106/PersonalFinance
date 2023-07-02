package pe.edu.cibertec.personalfinance.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.repository.CategoryRepository
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import pe.edu.cibertec.personalfinance.util.Result

@Composable
fun CategorySection(navController: NavController){
    val categories: List<Category>? = navController.previousBackStackEntry?.savedStateHandle?.get("categoryList")
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "hasChanged",
        value = false
    )
    val currentCategory = remember {
        mutableStateOf<Category?>(navController.currentBackStackEntry?.savedStateHandle?.get("category"))
    }
    LazyColumn(){
        items(categories!!.size/4) {
            Card() {
                val slicedArray = categories!!.slice(it*4..it*4+3)
                Row {
                    slicedArray.forEach { category->
                        Column(modifier = Modifier
                            .weight(4f)
                            .padding(8.dp)
                        ) {
                            Row(){
                                Column(modifier = Modifier.background(color =
                                if(currentCategory.value?.id == category.id) Color.LightGray
                                else Color.White
                                )) {
                                    IconButton(modifier = Modifier
                                        .background(
                                            shape = CircleShape,
                                            color = category.getCategoryColor()
                                        ), onClick = {
                                        currentCategory.value = category
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            key = "hasChanged",
                                            value = true
                                        )
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            key = "category",
                                            value = category
                                        )
                                    }) {
                                        Icon(
                                            ImageVector.vectorResource(id = category.getCategoryIcon()),
                                            contentDescription = category.title
                                        )
                                    }
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
        if(categories!!.size%4 != 0){
            item{
                Card() {
                    val slicedArray = categories!!.takeLast(categories!!.size%4)
                    Row {
                        slicedArray.forEach { category->
                            Column(modifier = Modifier
                                .weight(4f)
                                .padding(8.dp)) {
                                Row{
                                    Column(modifier = Modifier.background(color =
                                    if(currentCategory.value?.id == category.id) Color.LightGray
                                    else Color.White
                                    )) {
                                        IconButton(modifier = Modifier
                                            .background(
                                                shape = CircleShape,
                                                color = category.getCategoryColor()
                                            ), onClick = {
                                            currentCategory.value = category
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "hasChanged",
                                                value = true
                                            )
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "category",
                                                value = category
                                            )
                                        }) {
                                            Icon(
                                                ImageVector.vectorResource(id = category.getCategoryIcon()),
                                                contentDescription = category.title
                                            )
                                        }
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
                        repeat(4-categories!!.size%4){
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
fun CategorySectionDefaultPreview(){
    PersonalFinanceTheme {
        CategorySection(navController = rememberNavController())
    }
}