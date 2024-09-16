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
import com.example.fitz.ui.components.WorkoutChips
import com.example.fitz.ui.theme.DeepBlue
import com.example.fitz.ui.theme.TextWhite
import com.example.fitz.userData

@Composable
fun ArmWorkout(userData: userData?, navController: NavController) {

    val workOutToUrlMap1 = mapOf(
        "DumbbellCurl" to "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4",
        "BarbellCurl" to "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4",
        "HammerCurl" to "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4",
        "ConcentrationCurl" to "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4",
        "DumbbellReverseCurl" to "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4"
    )
    val workOutToStepsMap = mapOf(
        "DumbbellCurl" to listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position."
        ),
        "BarbellCurl" to listOf(
            "Hold the barbell with a shoulder-width grip.",
            "Keep your elbows close to your torso and lift the barbell towards your shoulders.",
            "Contract your biceps at the top and then lower the barbell slowly.",
            "Maintain a controlled motion throughout."
        ),
        "HammerCurl" to listOf(
            "Stand upright with a dumbbell in each hand, arms at the sides.",
            "Keep your elbows close to your torso and maintain a neutral grip (palms facing inward).",
            "Curl the dumbbells while keeping the upper arms stationary.",
            "Contract your biceps and hold the top position briefly.",
            "Slowly lower the dumbbells back to the starting position."
        ),
        "ConcentrationCurl" to listOf(
            "Sit on a flat bench with your legs spread and hold a dumbbell in one hand.",
            "Rest your elbow on the inner thigh of the same side.",
            "Curl the dumbbell upward while contracting your biceps.",
            "Pause briefly at the top and then lower the weight slowly."
        ),
        "DumbbellReverseCurl" to listOf(
            "Hold a dumbbell in each hand with your palms facing your thighs.",
            "Keeping your elbows close to your torso, lift the weights towards your shoulders.",
            "Contract your forearms at the top and then lower the dumbbells slowly.",
            "Maintain control throughout the movement."
        )
    )


    val workoutToUrlMap = mapOf(
        "Tricep Dips" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Tricep Kickback" to "https://media.istockphoto.com/id/1492806635/video/3d-rendered-animation-showcasing-the-tricep-exercise-with-dumbbells-on-the-white-background.mp4?s=mp4-480x480-is&k=20&c=8E8L5OFE-tEcAh0pnjPZM0kICgd1TN3GcwApgs8RGC4=",
        "Overhead Tricep Extension" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Skull Crushers" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Close-Grip Bench Press" to "https://assets.mixkit.co/videos/24309/24309-720.mp4"
    )
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 35.dp, bottom = 100.dp)
    ) {

        Greeting(name = "Arul", subTitle = "Here your arm workouts!🔥",userData,navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {

            item {

                Text(
                    text = "Bicep Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workOutToUrlMap1,workOutToStepsMap,
                    navController
                )
            }

            item {
                Text(
                    text = "Tricep Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workoutToUrlMap,workOutToStepsMap,navController
                )
            }
        }
    }
}