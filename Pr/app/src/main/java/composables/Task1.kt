package composables

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.random.Random

@Composable
fun DisplayElement1(){
    //State для оновлення ранодомного числа, який зберігається при рекомпозиції
    var load by rememberSaveable { mutableStateOf((Random.nextInt(0,100))) }
    //Змінна для анімації при оновленні числа
    val animateLoad by animateIntAsState(
        targetValue = load,
        animationSpec = tween(1000)
    )
    //Column щоб кожен елемент був в окремому рядку
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        //Заголовок сторінки
        Text(modifier = Modifier.padding(10.dp),fontSize = 28.sp, fontWeight = FontWeight(700), color = Color.Cyan, text = "Навантаження на мережу")
        //Поле для відображення рандомного числа, яке імітує навантаження
        //Використано MaterialTheme для стилізації тексту
        Text(modifier = Modifier.padding(10.dp),color = MaterialTheme.colorScheme.inverseOnSurface,fontSize = 20.sp, text = "Поточне навантаження:" + animateLoad.toString())
        //Кнопка для оновлення числа
        //Використано MaterialTheme для стилізації тексту та самої кнопки
        Button(modifier = Modifier.padding(10.dp),onClick = { load = Random.nextInt(0,100)},colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        )) {
            Text(text = "Оновити навантаження")
        }
    }
}