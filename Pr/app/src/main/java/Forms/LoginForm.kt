package Forms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import composables.ButtonLogin
import composables.CustomTextField
import composables.Title

//Composable функція для конфігурації форми авторизації
@Composable
fun LoginForm(){
    //Стани  для збереження введених даних користувачем для подальшої авторизації
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    //Змінна для повідомлення про помилки введення
    var errorMessage by rememberSaveable {mutableStateOf("") }

    Title("Авторизація")
    CustomTextField("Пошта", {email = it})
    CustomTextField("Пароль", {password = it})

    ButtonLogin(email, password,{errorMessage = it})

    Text(color= Color.Red,text = errorMessage)
}