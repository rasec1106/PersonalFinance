package pe.edu.cibertec.personalfinance.ui.Home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.Login.Models.LoginScreen
import pe.edu.cibertec.personalfinance.Login.Models.SignUpScreen


@Composable
fun Home() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Login.route) {

        composable(Route.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(Route.Login.route) {
            LoginScreen(navController)
        }

        composable(Route.EntryList.route){

        }

    }
}

sealed class Route(val route: String) {
    object Login : Route("login")
    object EntryList : Route ("entry_list")
    object SignUp : Route("sign_up")
}