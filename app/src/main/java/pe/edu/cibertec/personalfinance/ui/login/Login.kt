package pe.edu.cibertec.personalfinance.Login.Models



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.cibertec.personalfinance.R
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.data.repository.UserRepository
import pe.edu.cibertec.personalfinance.ui.Route
import pe.edu.cibertec.personalfinance.ui.util.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    HeaderImagePrueba()
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){

        /*Login(Modifier.align(Alignment.Center),navController)*/

            val userRepository = UserRepository()
            val context = LocalContext.current
            val user = remember{ mutableStateOf(TextFieldValue())}
            val password = remember{ mutableStateOf(TextFieldValue())}
            val showPassword = remember { mutableStateOf((false))}


            Column() {
                HeaderImageL(Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(16.dp))

                /*username field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp),
                    placeholder = { Text(text = "Usuario") },
                    value = user.value,
                    onValueChange = {user.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    maxLines = 1,

                    leadingIcon = { Icon(Icons.Default.Person, null)
                    },
                    shape = RoundedCornerShape(20.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                /*----------PASWORD FIELD AND SHOWPASSWORD FIELD------*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = { Text(text = "Password") },
                    value = password.value,
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {password.value = it},
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = if (showPassword.value) {
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showPassword.value = !showPassword.value
                            }) {
                            if (showPassword.value) {
                                Icon(Icons.Default.Visibility, null)
                            } else {
                                Icon(Icons.Default.VisibilityOff, null)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))

                /*-----forgot password---*/
                Text(
                    text = "¿Olvidaste la contraseña?",
                    modifier = Modifier.clickable { }.align(Alignment.End),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xA9020202)

                )
                Spacer(modifier=Modifier.padding(16.dp))

                /*---LOGIN BUTTON---*/
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors=ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xAB0389FF),

                        contentColor = androidx.compose.ui.graphics.Color.White,
                        disabledContentColor = androidx.compose.ui.graphics.Color.White),
                    shape = RoundedCornerShape(15.dp),
                    onClick = {
                        userRepository.login(user.value.text, password.value.text) { result ->
                            if (result is Result.Success) {
                                navController.navigate(Route.Entries.route)
                            } else {
                                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }) {
                    Text(text = "Iniciar Sesion")
                }
                Spacer(modifier=Modifier.padding(2.dp))

                /*---Registrarse BUTTON---*/

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors=ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xAB0389FF),
                        disabledBackgroundColor = Color(0xFF586DF7),
                        contentColor = androidx.compose.ui.graphics.Color.White,
                        disabledContentColor = androidx.compose.ui.graphics.Color.White),
                    shape = RoundedCornerShape(15.dp),
                    onClick = {
                        navController.navigate(Route.SignUp.route)

                    }

                ) {
                    Text(text = "Registrarse")
                }

            }

    }

}



@Composable
fun Login(modifier: Modifier,navController: NavController) {

    val userRepository = UserRepository()
    val context = LocalContext.current
    val user = remember{ mutableStateOf(TextFieldValue())}
    val password = remember{ mutableStateOf(TextFieldValue())}
    val showPassword = remember { mutableStateOf((false))}


    Column(modifier = modifier) {
        HeaderImageL(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))

        /*username field*/

            TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            placeholder = { Text(text = "Usuario") },
            value = user.value,
            onValueChange = {user.value = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,

            leadingIcon = { Icon(Icons.Default.Person, null)
            },
            shape = RoundedCornerShape(20.dp)
        )

            Spacer(modifier = Modifier.padding(16.dp))

        /*----------PASWORD FIELD AND SHOWPASSWORD FIELD------*/

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Password") },
            value = password.value,
            singleLine = true,
            maxLines = 1,
            onValueChange = {password.value = it},
            shape = RoundedCornerShape(20.dp),
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                    if (showPassword.value) {
                        Icon(Icons.Default.Visibility, null)
                    } else {
                        Icon(Icons.Default.VisibilityOff, null)
                    }
                }
            }
        )
            Spacer(modifier = Modifier.padding(8.dp))

        /*-----forgot password---*/
        Text(
            text = "¿Olvidaste la contraseña?",
            modifier = modifier.clickable { }.align(Alignment.End),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xA9020202)

        )
            Spacer(modifier=Modifier.padding(16.dp))

        /*---LOGIN BUTTON---*/
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors=ButtonDefaults.buttonColors(
                backgroundColor = Color(0xAB0389FF),

                contentColor = androidx.compose.ui.graphics.Color.White,
                disabledContentColor = androidx.compose.ui.graphics.Color.White),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                userRepository.login(user.value.text, password.value.text) { result ->
                    if (result is Result.Success) {
                        navController.navigate(Route.Entries.route)
                    } else {
                        Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }) {
            Text(text = "Iniciar Sesion")
        }
            Spacer(modifier=Modifier.padding(2.dp))

        /*---Registrarse BUTTON---*/

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors=ButtonDefaults.buttonColors(
                backgroundColor = Color(0xAB0389FF),
                disabledBackgroundColor = Color(0xFF586DF7),
                contentColor = androidx.compose.ui.graphics.Color.White,
                disabledContentColor = androidx.compose.ui.graphics.Color.White),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                navController.navigate(Route.SignUp.route)

                }

    ) {
        Text(text = "Registrarse")
    }

    }
        }







@Composable
fun HeaderImageL(modifier: Modifier) {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = "Header",
        modifier =modifier)
}

@Composable
fun HeaderImagePrueba() {
    Image(painter = painterResource(
        id = R.drawable.headerprueba),
        contentDescription = "Header",

        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
}




@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    PersonalFinanceTheme{
        LoginScreen(navController = rememberNavController())
    }
}

