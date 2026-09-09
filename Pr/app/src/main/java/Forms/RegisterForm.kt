package Forms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import composables.ButtonReg
import composables.CustomTextField
import composables.Title
//Composable функція для конфігурації форми реєстрації
@Composable
fun RegisterForm(){
    //Стани  для збереження введених даних користувачем для подальшої реєстрації
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    //Змінна для повідомлення про помилки введення
    var errorMessage by rememberSaveable {mutableStateOf("") }

    Title("Реєстрація")
    CustomTextField("Ім'я", {name = it})
    CustomTextField("Пошта", {email = it})
    CustomTextField("Пароль", {password = it})
    CustomTextField("Підтвердження паролю", {repeatPassword = it})

    ButtonReg(name, email, password,repeatPassword,{errorMessage = it})

    Text(color= Color.Red,text = errorMessage)
}