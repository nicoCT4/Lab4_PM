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
import java.util.Stack

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
    var subtitle by remember { mutableStateOf("") }

    Column(modifier= Modifier
        .padding(8.dp)
        .background(Color.Black)) {
        DisplayCalc(title, subtitle)

        Spacer(modifier = Modifier.height(8.dp))

        //Primera fila
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = {
                title = "0"
                subtitle = ""
            }, text = "C", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "(" else title + "("
            }, text = "(", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") ")" else title + ")"
            }, text = ")", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title.length > 1) {
                    title.dropLast(1) // Elimina el último carácter
                } else {
                    "0" // Si solo queda un carácter, restablece a "0"
                }
            }, text = "<<", backgroundColor = Color.Yellow, contentColor = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Segunda Fila
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "7" else title + "7"
            }, text = "7", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "8" else title + "8"
            }, text = "8", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "9" else title + "9"
            }, text = "9", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "*" else title + "*"
            }, text = "X", backgroundColor = Color.Yellow, contentColor = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Tercera fila
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "4" else title + "4"
            }, text = "4", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "5" else title + "5"
            }, text = "5", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "6" else title + "6"
            }, text = "6", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "-" else title + "-"
            }, text = "-", backgroundColor = Color.Yellow, contentColor = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Cuarta fila
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "1" else title + "1"
            }, text = "1", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "2" else title + "2"
            }, text = "2", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "3" else title + "3"
            }, text = "3", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "+" else title + "+"
            }, text = "+", backgroundColor = Color.Yellow, contentColor = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Quinta fila
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "." else title + "."
            }, text = ".", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title != "0") title + "0" else "0"
            }, text = "0", backgroundColor = Color.White, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                title = if (title == "0") "/" else title + "/"
            }, text = "/", backgroundColor = Color.Yellow, contentColor = Color.Black)
            CalculatorButton(onClickedValue = {
                try {
                    val expresionPostfija = Conversor.postFixConversion(title)
                    if (expresionPostfija == "Error") {
                        subtitle = "Expresión inválida"
                    } else {
                        val resultado = OperarPostfix().evaluarPostfix(expresionPostfija)
                        subtitle = resultado.toString()
                    }
                } catch (e: Exception) {
                    subtitle = "Error: ${e.message}"
                }
            }, text = "=", backgroundColor = Color.Yellow, contentColor = Color.Black)
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
            .padding(16.dp)
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

class OperarPostfix {
    /**
     * Evalúa una expresión postfija y devuelve el resultado como un Double.
     * Utiliza una pila para manejar los operandos y realizar las operaciones en el orden correcto.
     *
     * @param expresion La cadena de texto que contiene la expresión postfija a evaluar.
     * @return El resultado de la evaluación de la expresión.
     * @throws ArithmeticException Si ocurre una división por cero.
     * @throws IllegalArgumentException Si se encuentra un operador desconocido o la expresión es inválida.
     */
    fun evaluarPostfix(expresion: String): Double {
        val stack = Stack<Double>() // Pila para almacenar los operandos durante la evaluación.
        val elementos = expresion.split(" ")// Divide la expresión en elementos separados por espacios.

        for (elemento in elementos) {
            when {
                elemento.toDoubleOrNull() != null -> stack.push(elemento.toDouble())
                // Si el elemento es un número, lo convierte a Double y lo empuja a la pila.
                stack.size >= 2 -> {
                    // Asegura que hay al menos dos elementos en la pila para realizar una operación.
                    val b = stack.pop()
                    val a = stack.pop()
                    when (elemento) {
                        // Realiza la operación correspondiente al operador actual y empuja el resultado a la pila.
                        "+" -> stack.push(a + b)
                        "-" -> stack.push(a - b)
                        "*" -> stack.push(a * b)
                        // Lanza una excepción en caso de división por cero.
                        "/" -> if (b == 0.0) throw ArithmeticException("División por cero") else stack.push(a / b)
                        "^" -> stack.push(Math.pow(a, b))
                        else -> throw IllegalArgumentException("Operador desconocido: $elemento")
                    }
                }
                else -> throw IllegalArgumentException("Expresión inválida.")
            }
        }
        // Devuelve el resultado final de la pila, que es el resultado de evaluar toda la expresión.
        return stack.pop()
    }
}

class Conversor {
    companion object {
        // Método para convertir infix a postfix
        fun postFixConversion(string: String): String {
            var resultado = "" // Variable que almacenará el resultado en notación postfix
            val stack = ArrayDeque<Char>() // Pila para manejar operadores y paréntesis
            var i = 0 // Índice para recorrer la cadena de entrada

            while (i < string.length) {
                val s = string[i] // Carácter actual

                if (s.isDigit()) { // Si el carácter es un dígito
                    resultado += s // Agregar el dígito al resultado
                    // Manejar números de múltiples dígitos
                    while (i + 1 < string.length && string[i + 1].isDigit()) {
                        resultado += string[i + 1] // Agregar dígitos adicionales al resultado
                        i++ // Avanzar el índice
                    }
                    resultado += " " // Agregar un espacio después del número
                } else if (s == '(') { // Si el carácter es un paréntesis de apertura
                    stack.push(s) // Empujar el paréntesis en la pila
                } else if (s == ')') { // Si el carácter es un paréntesis de cierre
                    // Desapilar y agregar al resultado hasta encontrar un paréntesis de apertura
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        resultado += "${stack.pop()} "
                    }
                    if (stack.isNotEmpty()) stack.pop() // Eliminar el paréntesis de apertura
                } else if (notNumeric(s)) { // Si el carácter es un operador
                    // Desapilar y agregar al resultado mientras el operador en la cima de la pila tenga mayor o igual precedencia
                    while (stack.isNotEmpty() && operatorPrecedence(s) <= operatorPrecedence(stack.peek()!!)) {
                        resultado += "${stack.pop()} "
                    }
                    stack.push(s) // Empujar el operador en la pila
                }
                i++ // Avanzar el índice
            }

            // Desapilar y agregar al resultado todos los operadores restantes en la pila
            while (stack.isNotEmpty()) {
                if (stack.peek() == '(') return "Error" // Si queda un paréntesis de apertura, hay un error en la expresión
                resultado += "${stack.pop()} "
            }
            return resultado.trim() // Devolver el resultado sin espacios adicionales al final
        }

        // Método para verificar si un carácter no es un dígito
        fun notNumeric(ch: Char): Boolean = when (ch) {
            '+', '-', '*', '/', '(', ')', '^' -> true // Operadores y paréntesis no son numéricos
            else -> false // Cualquier otro carácter se considera numérico
        }

        // Método para determinar la precedencia de un operador
        fun operatorPrecedence(ch: Char): Int = when (ch) {
            '+', '-' -> 1 // Suma y resta tienen la precedencia más baja
            '*', '/' -> 2 // Multiplicación y división tienen precedencia intermedia
            '^' -> 3 // La exponenciación tiene la precedencia más alta
            else -> -1 // Cualquier otro carácter tiene precedencia inválida
        }

        // Funciónes de extensión
        fun <T> ArrayDeque<T>.push(element: T) = addLast(element)
        fun <T> ArrayDeque<T>.pop() = removeLastOrNull()
        fun <T> ArrayDeque<T>.peek() = lastOrNull()
    }
}



@Preview(showBackground = true)
@Composable
fun RightAlignedCardPreview() {
    Calculator()
}

@Preview(showBackground = true)
@Composable
fun DisplayCalcPreview(){
    DisplayCalc("", "o")
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview(){
    CalculatorButton({}, "0")
}