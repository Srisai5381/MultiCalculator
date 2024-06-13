package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multicalculator.android.ui.theme.MultiCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiCalculatorTheme {
                CalcView()
            }
        }
    }
}
//            MyApplicationTheme {
//
//                    Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    GreetingView(Calculator().add(5,7).toString())
//                    GreetingView(Calculator().subtract(8,4).toString())
//                    GreetingView(Calculator().multiply(2,4).toString())
//                    GreetingView(Calculator().divide(9,3).toString())
//                }
//            }
//        }


@Composable
fun CalcView() {
    val displayValue = remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            CalcDisplay(displayValue)
        }
        Row {
            Column(modifier = Modifier.weight(3f)) {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(displayValue, i, 3)
                }
                Row {
                    CalcNumericButton(0, displayValue)
                    CalcEqualsButton(displayValue)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                val operations = listOf("+", "-", "*", "/")
                operations.forEach { operation ->
                    CalcOperationButton(operation, displayValue)
                }
            }
        }
    }
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, buttonCount: Int) {
    val endNum = startNum + buttonCount
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(i, display)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        color = Color.Black
    )
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            if (display.value == "0") {
                display.value = number.toString()
            } else {
                display.value += number.toString()
            }
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = {
            display.value += " $operation "
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>) {
    Button(
        onClick = { display.value = "0" },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}


//@Composable
//fun GreetingView(text: String) {
//    Text(text = text)
//}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        GreetingView("Hello, Android!")
//    }
//}
