package com.example.myapplication
import android.content.SharedPreferences
import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
mainui()
            }
        }
    }

@Composable
fun mainui() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("myPrefFile", Context.MODE_PRIVATE)

    var count by remember {
        mutableStateOf(
            sharedPreferences.getInt("savedCount", 0) // Load initial value
        )
    }

    fun saveCount() {
        val editor = sharedPreferences.edit()
        editor.putInt("savedCount", count)
        editor.apply() // Use apply() for better performance
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: ${count}")
        Row {
            Button(onClick = { count++ },enabled = count < 100) {
                Text("Increment")
                saveCount()
            }
            Button(onClick = { if (count > 0) count-- }, enabled = count > 0) {
                Text("Decrement")
                saveCount()
            }
        }
    }
}