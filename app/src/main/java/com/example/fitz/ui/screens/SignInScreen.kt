package com.example.fitz.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitz.R
import com.example.fitz.classes.SignInState

@Composable
fun SignInScreen(
    state: SignInState= SignInState(),
    onSignInClick: () -> Unit={}
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {

        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }

    }
    val backgroundAlpha = remember { Animatable(-0.5f) }
    val boxAlpha = remember { Animatable(0.4f) }

    // Launch the effect to animate the alpha value
    LaunchedEffect(Unit) {
        backgroundAlpha.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.signin_bg_black),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = backgroundAlpha.value),
            contentScale = ContentScale.Crop
        )

    }

    // Launch the effect to animate the alpha value
    LaunchedEffect(Unit) {
        boxAlpha.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Image(
            painter = painterResource(id = R.drawable.font),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 330.dp)
                .size(120.dp)
                .graphicsLayer(alpha = 0.4f)
        )


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(90.dp)
                .width(300.dp)
                .padding(bottom = 26.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF717377).copy(alpha = boxAlpha.value),
                            Color(0xFF0C0A0A).copy(alpha = boxAlpha.value)
                        )
                    )
                )
                .clickable {
                    onSignInClick()
                }
            
        ) {
            Text(text = "Let's Get Started")
        }
    }
}




