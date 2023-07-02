package pe.edu.cibertec.personalfinance.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.ui.categories.CategoryList
import pe.edu.cibertec.personalfinance.ui.entries.EntryDetail
import pe.edu.cibertec.personalfinance.ui.entries.EntryList

@Composable
fun Home(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Entries.route){
        composable(Route.Entries.route){
            EntryList(navController)
        }
        composable(Route.Categories.route){
            CategoryList(navController)
        }
        composable(Route.EntryDetail.route){
            EntryDetail(navController)
        }
    }
}

// a sealed class is like an enum
sealed class Route(val route: String){
    object Entries: Route("entries")
    object Categories: Route("categories")
    object EntryDetail: Route("entry-detail")
}