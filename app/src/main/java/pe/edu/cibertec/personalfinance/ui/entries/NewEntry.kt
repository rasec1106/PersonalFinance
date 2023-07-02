package pe.edu.cibertec.personalfinance.ui.entries

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.data.repository.EntryRepository
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import pe.edu.cibertec.personalfinance.util.Result

@Composable
fun NewEntry(){
    val amount = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }
    val comment = remember {
        mutableStateOf("")
    }
    val type = 0
    val category = Category(1,"Comida","FOOD","#ff0000")

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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp, 8.dp, 0.dp),
            onClick = {
                val entry = Entry(0,amount.value.toDouble(),category,date.value,comment.value,type)
                entryRepository.createEntry(1, context, entry){ result ->
                    if(result is Result.Success){
                        Toast.makeText(context, "CREADO", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text(text = "Sign in")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewEntryDefaultPreview(){
    PersonalFinanceTheme {
        NewEntry()
    }
}