package composables

import Forms.RegisterForm
import Forms.LoginForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

//Enum для переходу між завданнями
enum class Tasks{
    TASK_1, TASK_2, TASK_3, TASK_4
}
//16. Мережа передачі електроенергії.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Scaffold, який використовується для отримання innerPadding
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(
                    //Використано модифікатор для скролу якщо вміст по висоті більший ніж розмір телефону
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Pr1Menu(innerPadding = innerPadding)
                }
            }
        }
    }
}
//Composable функція для сворення головного каркасу пр1, який включає кнопки для переключення завдань
//та поле де будуть відображені завдання
@Composable
fun Pr1Menu(innerPadding: PaddingValues = PaddingValues()){
    //Стан для зереження значення енам за допомогою якого відбувається вибір завдання
    var currentTask by rememberSaveable {mutableStateOf(Tasks.TASK_1)}
    //Column для відобреження кнопок в кожному рядку
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Кнопки, які змінюють значення змінної для відображення іншого завдання
        Button(
            onClick = {currentTask = Tasks.TASK_1},
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        )
        {
            Text(text = "Перше завдання")
        }
        Button(
            onClick = {currentTask = Tasks.TASK_2},
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        )
        {
            Text(text = "Друге завдання")
        }
        Button(
            onClick = {currentTask = Tasks.TASK_3},
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        )
        {
            Text(text = "Третє завдання")
        }
        Button(
            onClick = {currentTask = Tasks.TASK_4},
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        )
        {
            Text(text = "Четверте завдання")
        }
    }
    //Box більш мобільний для розміщення елементів
    //було використано RoundedCornerShape для закруглення країв елемента
    Box(
        modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.onSurface).fillMaxWidth(0.9f).defaultMinSize(minHeight = 500.dp).padding(top = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        //Слідкує за зміною стану та відображає потрібну задачу в іншому випадку
        //попереджає про те, що екран в розробці
        when (currentTask) {
            Tasks.TASK_1 -> DisplayElement1()
            Tasks.TASK_2 -> task2Menu()
            Tasks.TASK_3 -> task3Menu()
            Tasks.TASK_4 -> task4Menu()
            else -> Text(color = MaterialTheme.colorScheme.inverseOnSurface, text = "Цей екран ще в розробці")
        }
    }
}
//Composable для задачі 2
@Composable
fun task2Menu(){
    LineOperatingCurrent()
}
//Composable для задачі 3
@Composable
fun task3Menu(){
    Column(
        modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        //Змінні  для збереження стану погоди та стандартні змінні для випадкових значень генерування енергії відносно погоди
        var typeWeather by rememberSaveable{ mutableStateOf("Сонячно")}
        var percentGenPanel = 0.0
        var  percentGenWind = 0.0
        Text(modifier = Modifier.padding(0.dp, 10.dp),textAlign = TextAlign.Center,fontSize = 28.sp , color = Color.White,text = "Прогноз генерації електроенергії відносно погодних умов")
        //Виклик функції для відображення кнопки, яка при натисканні змінює typeWeather за допомогою анонімної фунуції
        WeatherDynamicBtn(typeWeather, onWeatherChange = {typeWeather = it})
        SolarWindPowerImg()
        //Відслідковування стану typeWeather для генерування даних відносно заданої погоди
        when(typeWeather){
            "Сонячно" -> {
                percentGenPanel = 90 + 10 * Random.nextDouble()
                percentGenWind = 20 + 30 * Random.nextDouble()
            }
            "Вітряно" -> {
                percentGenPanel = 50 + 40 * Random.nextDouble()
                percentGenWind = 80 + 20 * Random.nextDouble()
            }
            "Шторм" -> {
                percentGenPanel = 10 * Random.nextDouble()
                percentGenWind = 0.0
            }
            "Туман" -> {
                percentGenPanel = 5 + 15 * Random.nextDouble()
                percentGenWind = 10 + 30 * Random.nextDouble()
            }
        }
        //Відображення карток з даними(тип погоди, тип генератора та відсоток генерації)
        SolarWindPowerInfoCard(typeWeather, "Сонячні панелі", percentGenPanel)
        SolarWindPowerInfoCard(typeWeather, "Вітряні генератори", percentGenWind)
    }
}
//Composable для задачі 4
@Composable
fun task4Menu(){
    //Стан для переключення сторінок авторизації та реєстрації
    var currentPage by rememberSaveable {mutableStateOf(0)}
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //currentPage відслідковується та завдяки чому перемальовуються форми
        when(currentPage){
            0 -> {
                RegisterForm()
                //"Кнопка" для переключення форми
                Text(modifier = Modifier.clickable{currentPage = 1}, color = Color.White, fontWeight = FontWeight(500), fontSize = 20.sp,text = "Вже є аккаунт?")
            }
            1 -> {
                LoginForm()
                //"Кнопка" для переключення форми
                Text(modifier = Modifier.clickable{currentPage = 0}, color = Color.White, fontWeight = FontWeight(500), fontSize = 20.sp,text = "Немає аккаунту?")
            }
        }
    }
}