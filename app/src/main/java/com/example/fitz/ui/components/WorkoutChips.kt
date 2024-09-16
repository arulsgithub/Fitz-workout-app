package com.example.fitz.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun WorkoutChips(
    workOut: Map<String, String>,
    steps: Map<String, List<String>>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        workOut.forEach { (workout, url) ->

            // Convert steps to JSON string and URL encode it
            val workoutSteps = steps[workout] ?: listOf()
            val stepsJson = Json.encodeToString(workoutSteps)
            val encodedSteps = URLEncoder.encode(stepsJson, StandardCharsets.UTF_8.toString())

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .clickable {
                        navController.navigate(
                            "workout/${Uri.encode(workout)}/${Uri.encode(url)}/$encodedSteps"
                        )
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1C3170),
                                Color(0xFF864042)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(text = workout, color = Color.White)
            }
        }
    }
}
