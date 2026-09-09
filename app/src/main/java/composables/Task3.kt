package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pw1.R

//Composable функція для відображення єдиної кнопки, яка буде змінювати стан погоди
@Composable
//onWeatherChange це параметр-функція, яка потрібна для зміни стану в іншій Composable функції, яка викликає дану
fun WeatherDynamicBtn(typeWeather: String, onWeatherChange: (String) -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.padding(0.dp, 10.dp).fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.White
            ),
            onClick = {
                if(typeWeather == "Сонячно"){
                    onWeatherChange("Вітряно")
                }
                else if(typeWeather == "Вітряно"){
                    onWeatherChange("Шторм")
                }
                else if(typeWeather == "Шторм"){
                    onWeatherChange("Туман")
                }
                else if(typeWeather == "Туман"){
                    onWeatherChange("Сонячно")
                }
            }
        ) {
            Text(text = "$typeWeather")
        }
    }
}
//Composable функція для відображення деякого зображення
@Composable
fun SolarWindPowerImg(){
    Image(
        //Саме зображення
        painter = painterResource(R.drawable.solarwindpower),
        modifier = Modifier.clip(RoundedCornerShape(8.dp)).fillMaxWidth(0.9f),
        contentDescription = "Електрогенерація",
        //Використано Crop для того, щоб зображення повністю заповнювало область
        contentScale = ContentScale.Crop
    )
}
//Composable для виведення картки з даними, а тобто тип погоди, тип генератора(сонячна панель або вітрогенератор)
//та потужність генерації деякого приладу при деякій погоді.
@Composable
fun SolarWindPowerInfoCard(weather: String, genType: String, genPercent: Double){
    Column(
        modifier = Modifier.padding(0.dp, 10.dp).clip(RoundedCornerShape(8.dp)).background(Color.White).fillMaxWidth(0.9f).padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(fontWeight = FontWeight(600), text = "Тип погоди: $weather")
        Text(fontWeight = FontWeight(600), text = "Тип генератора: $genType")
        Text(fontWeight = FontWeight(600), text = String.format("Відсоток генерації: %.2f", genPercent))

    }
}