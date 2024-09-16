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
fun ShoulderWorkout(userData: userData?, navController: NavController) {

    val workoutToUrlMap = mapOf(
        "Dumbbell Bench Seated Press" to "https://media.istockphoto.com/id/1496326941/video/animation-of-a-fit-man-exercising-dumbbell-bench-seated-press-fitness-workout.mp4?s=mp4-640x640-is&k=20&c=hFnbrHQDw1xmqwoPqsaIACsrMwn1YBFfauqWr3uby2M=",
        "Lateral Raises" to "https://media.istockphoto.com/id/1495656595/video/3d-rendered-animation-of-a-sportsman-training-with-dumbbells-isolated-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=98iEahwPG7t3ROjSwRLud5fcs5vTb0DXJcnx5xlvfQs=",
        "Front Raises" to "https://media.istockphoto.com/id/1496158641/video/3d-rendered-animation-of-a-sportsman-training-serratus-anterior-and-trapezius-muscles-with.mp4?s=mp4-640x640-is&k=20&c=pFFz-UPUyt1s3pxvjn6wq8nBzfRkECf15pRwpmAR4hw=",
        "External Rotation" to "https://media.istockphoto.com/id/1498230659/video/3d-rendered-animation-of-a-shoulder-exercise-example-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=95fIMcwz8gmQ7uYn8Sy-OPiIcCeNIpDWBFzW_xQPIGM=",
        "Hammer Front Raises (alternate)" to "https://media.istockphoto.com/id/1496193738/video/3d-rendered-animation-of-a-model-doing-exercise-with-dumbbells-on-the-empty-white-background.mp4?s=mp4-640x640-is&k=20&c=nT8tZ1A9oy-22xAi2YryQaGLJLG-IKHXQgdUng_lb2c=",
        "Shrugs" to "https://media.istockphoto.com/id/1495681191/video/3d-rendered-animation-of-shoulders-exercise-example-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=uWvrmwbcskg4PD0MxgvgdO0nSpOabS4eFh0z9R8M8a0="
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
                    text = "Shoulder Workouts",
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