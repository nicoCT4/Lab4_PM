package com.example.lab4_calculadorac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4_calculadorac.ui.theme.Lab4_CalculadoraCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4_CalculadoraCTheme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator(
    modifier: Modifier = Modifier
){
    var title by remember { mutableStateOf("0") }
    var subtitle by remember { mutableStateOf("0") }


    Column(modifier= Modifier
        .padding(8.dp)
        .background(Color.Black)) {
        DisplayCalc(title, subtitle)

        Spacer(modifier = Modifier.height(8.dp))

        //Primera fila
        Row (
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            , modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = { title = title + "C" }, text = "C", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "(" }, text = "(", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + ")" }, text = ")", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "<<" }, text = "<<", backgroundColor = Color.Yellow, contentColor = Color.Black)

        }

        Spacer(modifier = Modifier.height(8.dp))

        //Segunda Fila
        Row (
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            , modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = { title = title + "7" }, text = "7", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "8" }, text = "8", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "9" }, text = "9", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "X" }, text = "X", backgroundColor = Color.Yellow, contentColor = Color.Black)

        }
        Spacer(modifier = Modifier.height(8.dp))

        //Tercera fila
        Row (
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            , modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = { title = title + "4" }, text = "4", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "5" }, text = "5", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "6" }, text = "6", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "-" }, text = "-", backgroundColor = Color.Yellow, contentColor = Color.Black)

        }
        Spacer(modifier = Modifier.height(8.dp))

        //Cuarta fila
        Row (
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            , modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = { title = title + "1" }, text = "1", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "2" }, text = "2", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "3" }, text = "3", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "+" }, text = "+", backgroundColor = Color.Yellow, contentColor = Color.Black)

        }
        Spacer(modifier = Modifier.height(8.dp))

        //Quinta fila
        Row (
            verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center
            , modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = { title = title + "." }, text = ".", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "0" }, text = "0", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "/" }, text = "/", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = { title = title + "=" }, text = "=", backgroundColor = Color.Yellow, contentColor = Color.Black)

        }




    }


}

@Composable
fun CalculatorButton(
    onClickedValue: () -> Unit
    , text: String = ""
    , modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    contentColor: Color = Color.White
){
    Button(
        onClick = onClickedValue
        , modifier = modifier.padding(end = 15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text)
    }

}

@Composable
fun DisplayCalc(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RightAlignedCardPreview() {
    Calculator()
}