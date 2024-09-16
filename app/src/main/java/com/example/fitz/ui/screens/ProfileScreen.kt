package com.example.fitz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fitz.ui.theme.AquaBlue
import com.example.fitz.ui.theme.DeepBlue
import com.example.fitz.ui.theme.Purple80
import com.example.fitz.ui.theme.TextWhite
import com.example.fitz.userData
@Composable
fun ProfileScreen(
    userData: userData? = null,
    onSignOut: () -> Unit = {},
    onSignIn: () -> Unit = {},
    navController: NavController = rememberNavController()
) {
    var isSignedIn by remember { mutableStateOf(userData != null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top = 100.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isSignedIn) {
            if (userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (userData?.username != null) {
                Text(
                    text = userData.username,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    color = TextWhite,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 20.dp, end = 20.dp)
                        .background(AquaBlue)
                )
            }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (userData?.username != null) {
                            Text(
                                text = "Username",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Purple80,
                                //modifier = Modifier.padding(end = 30.dp)
                            )
                            Text(
                                text = userData.username,
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextWhite
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(start = 20.dp, end = 20.dp)
                            .background(AquaBlue)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (userData?.email != null) {
                            Text(
                                text = "Email-ID",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Purple80,
                                //modifier = Modifier.padding(end = 30.dp)
                            )
                            Text(
                                text = userData.email,
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextWhite
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(start = 20.dp, end = 20.dp)
                            .background(AquaBlue)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "\"This part is yet to be implemented by the developer\"",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Green,
                            //modifier = Modifier.padding(end = 30.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(start = 20.dp, end = 20.dp)
                            .background(AquaBlue)
                    )
                }
            Button(
                onClick = {
                    onSignOut()
                    isSignedIn = false
                    /*navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }*/
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(250.dp)

            ) {
                Text(text = "Sign out")
            }
        } else {
            Button(
                onClick = {
                    onSignIn()
                    isSignedIn = true
                   /*navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }*/
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(250.dp)
            ) {
                Text(text = "Sign in")
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun WithGreenBackground() {
    SignInScreen()
}*/
@Preview(showBackground = true)
@Composable
fun WithGreenBackground(){
    ProfileScreen()
}