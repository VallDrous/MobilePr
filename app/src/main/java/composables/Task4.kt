package composables

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//Composable функція для відображення заголовку
@Composable
fun Title(text: String){
    Text(color = Color.White,fontSize = 28.sp, textAlign = TextAlign.Center,text = text)
}
//Composable функція для відображення поля вводу
//Вона приймає параметр-функцію для того, щоб повертати значення введених даних для подальшого використання
@Composable
fun CustomTextField(title: String, fieldValue: (String) -> Unit){
    var fieldValueLocal by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = fieldValueLocal,
        //При зміні значення зберігається введене значення та викликається функція для отримання введеного значення
        onValueChange = {
            fieldValueLocal = it
            fieldValue(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        label = {Text(color = Color.White, text=title)}
    )
}
//Кнопка реєстрації, приймає параметри введені користувачем та
//параметр функцію для повернення повідомлення про неправельне заповнення даних
@Composable
fun ButtonReg(name: String, email: String, password: String, repeatPassword: String, errorMessage: (String) -> Unit){
    //Стан для збереження булевого значення валідації
    var isValid by rememberSaveable {mutableStateOf(true) }
    Button(
        modifier = Modifier.padding(0.dp, 10.dp),
        onClick = {
            //Прибираємо повідомлення про помилку, якщо воно є або немає
            errorMessage("")
            isValid = true
            //Регулярний вираз для перевірки правильного формату електронної пошти
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            //Валідація довжини рядка
            if(name.length <= 2){
                errorMessage("Ім'я повинно бути більше 1 символа!")
                isValid = false
            }
            //Перевірка на використання спец символів або чисел
            else if(!name.any{it.isLetterOrDigit()} || name.any {it.isDigit()}){
                errorMessage("Ім'я не повинне мати спец символи або цифри")
                isValid = false
            }
            //Перевірка на те, що поле пошта не пусте
            if(email.isEmpty()){
                errorMessage("Пошта не може бути пустою")
                isValid = false
            }
            //Валідація на правильний формат за допомогою регуляного виразу
            else if(!emailRegex.matches(email)){
                errorMessage("Некоректний формат пошти")
                isValid = false
            }
            //Перевірка на те, що поле пароль не пусте
            if(password.isEmpty()){
                errorMessage("Пароль не може бути пустим")
                isValid = false
            }
            //Перевірка на те, що поле пароль не починається з спец символу
            else if(!password[0].isLetterOrDigit()){
                errorMessage("Пароль не повинен починатися на спец символ")
                isValid = false
            }
            //Перевірка мін довжини паролю
            else if(password.length < 3){
                errorMessage("Пароль повинен бути більше 3 символів")
                isValid = false
            }
            //Перевірка на те, що введений знову пароль співпадає з паролем
            if(password != repeatPassword){
                errorMessage("Паролі не співпадають")
                isValid = false
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        )
    ) {
        Text("Зареєструватися")
    }
}
//Кнопка авторизації, приймає параметри введені користувачем та
//параметр функцію для повернення повідомлення про неправельне заповнення даних
@Composable
fun ButtonLogin(email: String, password: String, errorMessage: (String) -> Unit){
    var isValid by rememberSaveable {mutableStateOf(true) }
    Button(
        modifier = Modifier.padding(0.dp, 10.dp),
        onClick = {
            errorMessage("")
            isValid = true
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            //Перевірка на те, що поле пошта не пусте
            if(email.isEmpty()){
                errorMessage("Пошта не може бути пустою")
                isValid = false
            }
            //Валідація на правильний формат за допомогою регуляного виразу
            else if(!emailRegex.matches(email)){
                errorMessage("Некоректний формат пошти")
                isValid = false
            }
            //Перевірка на те, що поле пароль не пусте
            if(password.isEmpty()){
                errorMessage("Пароль не може бути пустим")
                isValid = false
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        )
    ) {
        Text("Авторизуватись")
    }
}