package composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import BusinesLogic.Calculate
import android.util.Log
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

//Composable для відображення елементів для обчислення робочого струму лінії
@Composable
fun LineOperatingCurrent(){
    //Стани для збереження введених даних для обчислення користувачем
    var p by rememberSaveable {mutableStateOf("Ввід...") }
    var u by rememberSaveable {mutableStateOf("Ввід...") }
    var cosFi by rememberSaveable {mutableStateOf("Ввід...") }

    //Стани для відображення повідомлення про помилку в введені
    var pError by rememberSaveable {mutableStateOf("") }
    var uError by rememberSaveable {mutableStateOf("") }
    var cosFiError by rememberSaveable {mutableStateOf("") }

    var res by rememberSaveable {mutableStateOf("") }
    Column() {
        //Заголовок
        Text(color = Color.White,fontSize = 28.sp, textAlign = TextAlign.Center,text = "Обчислення робочого струму лінії")
        //Поле для вводу даних для обчислення
        OutlinedTextField(
            modifier = Modifier.padding(5.dp).onFocusChanged{ focusState ->
                //Написано було для стандартного TextField для OutlinedTextField фактично не потрібно
                if(focusState.isFocused && p == "Ввід..."){
                    p = ""
                }
                else if(!focusState.isFocused && p == ""){
                    p = "Ввід..."
                }
            }.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            value = p,
            onValueChange = { p = it },
            label = { Text(color = Color.White,text = "активне навантаження (потужність), Ват (Вт)") },
        )
        Text(color = Color.Red,text = pError)
        OutlinedTextField(
            modifier = Modifier.padding(5.dp).onFocusChanged{ focusState ->
                if(focusState.isFocused && u == "Ввід..."){
                    u = ""
                }
                else if(!focusState.isFocused && u == ""){
                    u = "Ввід..."
                }
            }.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            value = u,
            onValueChange = { u = it },
            label = { Text(color = Color.White, text = "міжфазна (лінійна) напруга мережі, Вольт (В)") },
        )
        Text(color = Color.Red,text = uError)
        OutlinedTextField(
            modifier = Modifier.padding(5.dp).onFocusChanged{ focusState ->
                if(focusState.isFocused && cosFi == "Ввід..."){
                    cosFi = ""
                }
                else if(!focusState.isFocused && cosFi == ""){
                    cosFi = "Ввід..."
                }
            }.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            value = cosFi,
            onValueChange = { cosFi = it },
            label = { Text(color = Color.White, text="коефіцієнт потужності (безрозмірний, від 0 до 1)") },
        )
        Text(color = Color.Red,text = cosFiError)
        //У даному Row знаходиться кнопка, яка використовується для валідації даних та обчислення результату
        //з класу Calculate за допомогою фунції OperatingCurrentCalculate
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(0.9f),
                onClick = {
                    var isOk = true
                    pError = ""
                    uError = ""
                    cosFiError = ""

                    if(p.toDoubleOrNull() == null){
                        pError = "Потрібно ввести число!"
                        isOk = false
                    }
                    if(u.toDoubleOrNull() == null){
                        uError = "Потрібно ввести число!"
                        isOk = false
                    }
                    if(cosFi.toDoubleOrNull() == null){
                        cosFiError = "Потрібно ввести число!"
                        isOk = false
                    }
                    else if(cosFi.toDouble() < 0 || cosFi.toDouble() > 1){
                        cosFiError = "Коефіцієнт може бути від 0 до 1!"
                        isOk = false
                    }
                    if(isOk == true){
                        res = "Робочий струм лінії, Ампер (А): " + Calculate.OperatingCurrentCalculate(p.toDouble(), u.toDouble(), cosFi.toDouble()).toString()
                        //Log.d("111",Calculate.OperatingCurrentCalculate(p.toDouble(), u.toDouble(), cosFi.toDouble()).toString())
                    }
                }
            ) {
                Text("Обчислити робочий струм лінії, Ампер (А)")
            }
        }
        Text(modifier = Modifier.padding(10.dp),color = Color.White, text = "$res")
    }
}