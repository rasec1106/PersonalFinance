package pe.edu.cibertec.personalfinance.Login.Models



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.cibertec.personalfinance.R
import pe.edu.cibertec.personalfinance.ui.theme.PersonalFinanceTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.personalfinance.data.repository.UserRepository
import pe.edu.cibertec.personalfinance.ui.Route
import pe.edu.cibertec.personalfinance.ui.util.Result


@Composable
fun SignUpScreen(navController: NavController){

    HeaderImagePrueba()


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){



            val userRepository = UserRepository()
            val context = LocalContext.current
            val user = remember{ mutableStateOf(TextFieldValue())}
            val password = remember{ mutableStateOf(TextFieldValue())}
            val showPassword = remember { mutableStateOf((false))}
            val confirmPassword = remember { mutableStateOf(TextFieldValue())}
            val showConfirmPassword = remember { mutableStateOf((false))}
            val email= remember{ mutableStateOf(TextFieldValue())}
            val firstName= remember{ mutableStateOf(TextFieldValue())}
            val lastName= remember{ mutableStateOf(TextFieldValue())}
            val phone= remember{ mutableStateOf(TextFieldValue())}


        Column() {



                Spacer(modifier = Modifier.padding(16.dp))

                /*username field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),

                    placeholder = { Text(text = "Usuario") },
                    value = user.value,
                    onValueChange = {user.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),
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
                Spacer(modifier = Modifier.padding(10.dp))

                /*---CONFIRM PASSWORD---*/
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = { Text(text = "Confirmar Password") },
                    value = confirmPassword.value,
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {confirmPassword.value = it},
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = if (showConfirmPassword.value) {
                        VisualTransformation.None
                    }else{
                        PasswordVisualTransformation()
                    },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showConfirmPassword.value = !showConfirmPassword.value
                            }) {
                            if (showConfirmPassword.value) {
                                Icon(Icons.Default.Visibility, null)
                            } else {
                                Icon(Icons.Default.VisibilityOff, null)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))




                /*nombres field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),
                    placeholder = { Text(text = "Nombres") },
                    value = firstName.value,
                    onValueChange = {firstName.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    maxLines = 1,

                    leadingIcon = { Icon(Icons.Default.Person, null)
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))

                /*apellidos field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),
                    placeholder = { Text(text = "Apellidos") },
                    value = lastName.value,
                    onValueChange = {lastName.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    maxLines = 1,

                    leadingIcon = { Icon(Icons.Default.Person, null)
                    },
                    shape = RoundedCornerShape(20.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                /*correo field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),
                    placeholder = { Text(text = "Correo") },
                    value = email.value,
                    onValueChange = {email.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    maxLines = 1,

                    leadingIcon = { Icon(Icons.Default.Email, null)
                    },
                    shape = RoundedCornerShape(20.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                /*numero telefono field*/

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp,)
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .border(2.dp,Color.Gray,shape = RoundedCornerShape(20.dp)),

                    placeholder = { Text(text = "Telefono") },
                    value = phone.value,
                    onValueChange = {phone.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    maxLines = 1,

                    leadingIcon = { Icon(Icons.Default.Call, null)
                    },
                    shape = RoundedCornerShape(20.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))






                /*---SIGN UP BUTTON---*/

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(2.dp,Color.Transparent,shape = RoundedCornerShape(15.dp)),
                    colors=ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xAB0389FF),
                        disabledBackgroundColor = Color(0xFF586DF7),
                        contentColor = androidx.compose.ui.graphics.Color.White,
                        disabledContentColor = androidx.compose.ui.graphics.Color.White),
                    shape = RoundedCornerShape(15.dp),
                    onClick = {
                        userRepository.createUser(
                            user.value.text,
                            password.value.text,
                            confirmPassword.value.text,
                            email.value.text,
                            firstName.value.text,
                            lastName.value.text,
                            phone.value.text

                        ) { result ->
                            if (result is Result.Success) {
                                navController.navigate(Route.Login.route)
                            } else {
                                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                    }
                ) {Text(text = "Registrarse")
                }

            }
        }

    }

    










@Composable
fun HeaderImageS(modifier: Modifier) {
    Image(painter = painterResource(
        id = R.drawable.logo),
        contentDescription = "Header",
        modifier =modifier)
}




@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    PersonalFinanceTheme{
        SignUpScreen(navController = rememberNavController())
    }
}

