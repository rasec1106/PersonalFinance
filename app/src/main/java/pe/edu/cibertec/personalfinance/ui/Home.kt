package pe.edu.cibertec.personalfinance.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.Login.Models.LoginScreen
import pe.edu.cibertec.personalfinance.Login.Models.SignUpScreen
import pe.edu.cibertec.personalfinance.ui.categories.CategoryList
import pe.edu.cibertec.personalfinance.ui.entries.EntryDetail
import pe.edu.cibertec.personalfinance.ui.entries.EntryList

@Composable
fun Home(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Login.route){
        composable(Route.Entries.route){
            EntryList(navController)
        }
        composable(Route.Categories.route){
            CategoryList(navController)
        }
        composable(Route.EntryDetail.route){
            EntryDetail(navController)
        }
        composable(Route.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Route.Login.route) {
            LoginScreen(navController)
        }
    }
}

sealed class Route(val route: String){
    object Entries: Route("entries")
    object Categories: Route("categories")
    object EntryDetail: Route("entry-detail")
    object Login : Route("login")
    object SignUp : Route("sign-up")
}