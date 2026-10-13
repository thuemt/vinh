package com.example.bait1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bait1.ui.theme.Bait1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bait1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThucHanh03Screen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ThucHanh03Screen(modifier: Modifier = Modifier) {
    var num1Text by remember { mutableStateOf("") }
    var num2Text by remember { mutableStateOf("") }
    var selectedOperator by remember { mutableStateOf<String?>(null) }

    val resultText = remember(num1Text, num2Text, selectedOperator) {
        computeResult(selectedOperator, num1Text, num2Text)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Thực hành 03",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Input 1
        OutlinedTextField(
            value = num1Text,
            onValueChange = { num1Text = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Operators Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OperatorButton(
                symbol = "+",
                backgroundColor = Color(0xFFEA5455),
                isSelected = selectedOperator == "+",
                onClick = { selectedOperator = "+" }
            )
            OperatorButton(
                symbol = "-",
                backgroundColor = Color(0xFFE0A949),
                isSelected = selectedOperator == "-",
                onClick = { selectedOperator = "-" }
            )
            OperatorButton(
                symbol = "*",
                backgroundColor = Color(0xFF5B50E5),
                isSelected = selectedOperator == "*",
                onClick = { selectedOperator = "*" }
            )
            OperatorButton(
                symbol = "/",
                backgroundColor = Color(0xFF2D2D2D),
                isSelected = selectedOperator == "/",
                onClick = { selectedOperator = "/" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input 2
        OutlinedTextField(
            value = num2Text,
            onValueChange = { num2Text = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Result
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = if (resultText.isNotEmpty()) "Kết quả: $resultText" else "Kết quả:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun OperatorButton(
    symbol: String,
    backgroundColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        ),
        border = if (isSelected) BorderStroke(2.5.dp, Color.Black) else null,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun computeResult(operator: String?, num1Str: String, num2Str: String): String {
    if (operator == null) return ""
    val n1 = num1Str.toDoubleOrNull() ?: return ""
    val n2 = num2Str.toDoubleOrNull() ?: return ""

    val res = when (operator) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "*" -> n1 * n2
        "/" -> {
            if (n2 == 0.0) return "Không thể chia cho 0"
            n1 / n2
        }
        else -> return ""
    }

    return if (res % 1 == 0.0) {
        res.toLong().toString()
    } else {
        res.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun ThucHanh03Preview() {
    Bait1Theme {
        ThucHanh03Screen()
    }
}