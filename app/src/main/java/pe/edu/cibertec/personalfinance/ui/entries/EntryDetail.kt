package pe.edu.cibertec.personalfinance.ui.entries

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.data.repository.EntryRepository
import pe.edu.cibertec.personalfinance.ui.Route
import pe.edu.cibertec.personalfinance.ui.categories.CategoryList
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import pe.edu.cibertec.personalfinance.util.Result

@Composable
fun EntryDetail(navController: NavController){
    val selectedEntry: Entry? = navController.previousBackStackEntry?.savedStateHandle?.get<Entry>("entry")
    val selectedCategory = remember {
        mutableStateOf(selectedEntry?.category)
    }
    val amount = remember {
        mutableStateOf(selectedEntry?.amount.toString())
    }
    val date = remember {
        mutableStateOf(selectedEntry?.date.toString())
    }
    val comment = remember {
        mutableStateOf(selectedEntry?.comment.toString())
    }
    val type = 0
    val entryRepository = EntryRepository()
    val context = LocalContext.current

    
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cantidad") },
            value = amount.value,
            onValueChange = {
                amount.value = it
            }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Fecha") },
            value = date.value,
            onValueChange = {
                date.value = it
            }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Comentario") },
            value = comment.value,
            onValueChange = {
                comment.value = it
            }
        )
        Text(text = "Categoria")
        CategoryList(navController = navController)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp, 8.dp, 0.dp),
            onClick = {
                val hasChanged: Boolean? = navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>("hasChanged")
                if(hasChanged!!)
                    selectedCategory.value = navController.currentBackStackEntry?.savedStateHandle?.get<Category>("category")
                val entry = Entry(
                    selectedEntry?.id ?: 0,
                    amount.value.toDouble(),
                    selectedCategory.value!!,
                    date.value,
                    comment.value,
                    type
                )
                if(entry.id == 0){
                    entryRepository.createEntry(1, context, entry){ result ->
                        if(result is Result.Success){
                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                            navController.navigate(Route.Entries.route)
                        }else{
                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else{
                    entryRepository.updateEntry(1, context, entry, entry.id){ result ->
                        if(result is Result.Success){
                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                            navController.navigate(Route.Entries.route)
                        }else{
                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        ) {
            Text(text =
                if(selectedEntry?.id != 0) "Actualizar entrada"
                else "Crear entrada"
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp, 8.dp, 0.dp),
            onClick = {
                navController.navigate(Route.Entries.route)
            }
        ) {
            Text(text = "Cancelar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewEntryDefaultPreview(){
    PersonalFinanceTheme {
        EntryDetail(navController = rememberNavController())
    }
}