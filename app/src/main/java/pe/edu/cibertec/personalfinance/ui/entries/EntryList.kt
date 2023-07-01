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
import pe.edu.cibertec.personalfinance.data.repository.EntryRepository
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme

@Composable
fun EntryList(){
    val entries = remember{
        mutableStateOf(listOf<Entry>())
    }
    //entries.value = Entry.populateWithMockData()

    val context = LocalContext.current

    val entryRepository = EntryRepository()
    entryRepository.getEntries(1,context) {result ->
        entries.value = result.data!!
    }

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