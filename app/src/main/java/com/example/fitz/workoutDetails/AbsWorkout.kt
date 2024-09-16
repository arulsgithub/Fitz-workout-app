package com.example.fitz.workoutDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitz.ui.screens.Greeting
import com.example.fitz.ui.theme.DeepBlue
import com.example.fitz.ui.theme.TextWhite
import com.example.fitz.userData

@Composable
fun AbsWorkout(userData: userData?, navController: NavController) {

    val workoutToUrlMap = mapOf(
        "Leg Raise" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5001.mp4",
        "Crunchs" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5002.mp4",
        "Bicycle Crunch" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5006.mp4",
        "V Up " to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5010.mp4",
        "Full Plank" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5034.mp4",
        "Mountain Climber" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5025.mp4"
    )
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {

        Greeting(name = "Arul", subTitle = "Here your arm workouts!🔥",userData,navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {

            item {

                Text(
                    text = "ABS Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                /*WorkoutChips(
                    workoutToUrlMap,navController
                )*/
            }

        }
    }
}