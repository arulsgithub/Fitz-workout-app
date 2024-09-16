package com.example.fitz.ui.screens


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitz.classes.Feature
import com.example.fitz.R
import com.example.fitz.standardQuadTo
import com.example.fitz.ui.theme.Beige1
import com.example.fitz.ui.theme.Beige2
import com.example.fitz.ui.theme.Beige3
import com.example.fitz.ui.theme.BlueViolet1
import com.example.fitz.ui.theme.BlueViolet2
import com.example.fitz.ui.theme.BlueViolet3
import com.example.fitz.ui.theme.ButtonBlue
import com.example.fitz.ui.theme.DarkerButtonBlue
import com.example.fitz.ui.theme.DeepBlue
import com.example.fitz.ui.theme.LightGreen1
import com.example.fitz.ui.theme.LightGreen2
import com.example.fitz.ui.theme.LightGreen3
import com.example.fitz.ui.theme.OrangeYellow1
import com.example.fitz.ui.theme.OrangeYellow2
import com.example.fitz.ui.theme.OrangeYellow3
import com.example.fitz.ui.theme.TextWhite
import com.example.fitz.userData

@Composable
fun HomeScreen(userData: userData?, navController: NavController){

/*    val navController = rememberNavController()
    NavHost(navController, startDestination = "armWorkout") {
        composable("armWorkout") {
            ArmWorkout()
        }
    }*/


    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){


        Column (
            modifier = Modifier
                .padding(top = 30.dp)
        ){

            Greeting(name = "Arul", subTitle = "We wish you have a good day!",userData,navController)

            ChipSection(chip = listOf("Monday", "Tuesday","wednesday","Thursday","Friday","Saturday"))

            CurrentMeditation()

            FeaturedSection(features = listOf(
                Feature(
                    title = "Arm Exercises",
                    R.drawable.ic_arm,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3,
                    "armWorkout"
                ),
                Feature(
                    title = "Chest Exercises",
                    R.drawable.ic_chest,
                    OrangeYellow3,
                    OrangeYellow2,
                    OrangeYellow1,
                    "chestWorkout"
                ),
                Feature(
                    title = "Shoulder Exercises",
                    R.drawable.ic_shoulder,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3,
                    "shoulderWorkout"
                ),
                Feature(
                    title = "Back Exercises",
                    R.drawable.ic_back,
                    Beige1,
                    Beige2,
                    Beige3,
                    "backWorkout"
                ),
                Feature(
                    title = "ABS    Exercises",
                    R.drawable.ic_abs,
                    OrangeYellow3,
                    OrangeYellow2,
                    OrangeYellow1,
                    "absWorkout"
                ),
                Feature(
                    title = "Leg Exercises",
                    R.drawable.ic_leg,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3,
                    "legWorkout"
                ),
                Feature(
                    title = "Weight Lifting",
                    R.drawable.ic_deadlift,
                    Beige1,
                    Beige2,
                    Beige3,
                    "weightLifting"
                ),
                Feature(
                    title = "Full Body Workout",
                    R.drawable.ic_fullbody,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3,
                    "fullBodyWorkout"

                )
            ),navController)
        }
        /*Box(
            modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavigationMenu(
                items = listOf(
                    BottomMenuContents("Home", R.drawable.ic_home),
                    BottomMenuContents("Analyse", R.drawable.ic_analyse),
                    BottomMenuContents("Community", R.drawable.ic_community),
                    BottomMenuContents("Settings", R.drawable.ic_settings)
                )
            )
        }*/
    }

}

@Composable
fun Greeting(
    name: String = "Arul",
    subTitle: String = "",
    userData: userData?,
    navController: NavController
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center

        ){
            Text(
                text = "Good Morning, $name",
                color = TextWhite,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = subTitle,
                color = TextWhite,
                style = MaterialTheme.typography.bodySmall
            )
        }
        /*Icon(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )*/
        if(userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("profile")
                    },
                contentScale = ContentScale.Crop
            )
                /*Image(
                    painter = rememberAsyncImagePainter(userData.profilePictureUrl),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("profile")
                        },
                    contentScale = ContentScale.Crop
                )*/

        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
                    .clickable {
                    navController.navigate("profile")
                }
            )
        }
    }
}

@Composable
fun ChipSection(
    chip: List<String>
){
    var selectedChipIndex by remember {
        mutableIntStateOf(0)
    }

    LazyRow(
        modifier = Modifier
            .padding(end = 16.dp)
    ) {
        items(chip.size){

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .wrapContentWidth()

                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(16.dp)

            ){
                Text(text = chip[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Brush = Brush.linearGradient(
        colors = listOf(
            Color(0xfff1c746), // First color
            Color(0xff000000)  // Second color
        )
    )
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
    ){

        Column(
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Current Workout",
                color = TextWhite,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Workout Name",
                color = TextWhite,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp) // Adjust the size asneeded
                .background(Color.Black, shape = CircleShape)
                .padding(8.dp) // Add padding to create space around the icon
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Start", // Use a relevant description
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}

@Composable
fun FeaturedSection(features: List<Feature>, navController: NavController){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Featured",
            color = TextWhite,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp,end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {

            items(features.size){
                FeatureItems(feature = features[it],navController)
            }

        }
    }
}

@Composable
fun FeatureItems(
    feature: Feature, navController: NavController
){

    var screen = feature.screenName

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight


        val mediumColorPoint1 = Offset(0f,height*0.3f)
        val mediumColorPoint2 = Offset(width*0.1f,height*0.35f)
        val mediumColorPoint3 = Offset(width*0.4f,height*0.05f)
        val mediumColorPoint4 = Offset(width*0.75f,height*0.7f)
        val mediumColorPoint5 = Offset(width*1.4f,-height.toFloat())


        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x,mediumColorPoint1.y)

            standardQuadTo(mediumColorPoint1,mediumColorPoint2)
            standardQuadTo(mediumColorPoint2,mediumColorPoint3)
            standardQuadTo(mediumColorPoint3,mediumColorPoint4)
            standardQuadTo(mediumColorPoint4,mediumColorPoint5)

            lineTo(width.toFloat()*100f,height.toFloat()*100f)
            lineTo(-100f,height.toFloat()*100f)

            close()

        }

        val lightColorPoint1 = Offset(0f,height*0.35f)
        val lightColorPoint2 = Offset(width*0.1f,height*0.4f)
        val lightColorPoint3 = Offset(width*0.3f,height*0.35f)
        val lightColorPoint4 = Offset(width*0.65f,height.toFloat())
        val lightColorPoint5 = Offset(width*1.4f,-height.toFloat()/3f)


        val lightColorPath = Path().apply {
            moveTo(lightColorPoint1.x,lightColorPoint1.y)

            standardQuadTo(lightColorPoint1,lightColorPoint2)
            standardQuadTo(lightColorPoint2,lightColorPoint3)
            standardQuadTo(lightColorPoint3,lightColorPoint4)
            standardQuadTo(mediumColorPoint4,lightColorPoint5)

            lineTo(width.toFloat()*100f,height.toFloat()*100f)
            lineTo(-100f,height.toFloat()*100f)

            close()

        }
        Canvas(modifier = Modifier.fillMaxSize()) {

            drawPath(
                path = mediumColorPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightColorPath,
                color = feature.lightColor
            )

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ){
            Text(
                text = feature.title,
                color = TextWhite,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title, // Use a relevant description
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(45.dp)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate(screen)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )

        }

    }
}

/*
@Composable
fun BottomNavigationMenu(
    items: List<BottomMenuContents>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
){
    var selectedItemIndex by remember {
        mutableIntStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
    ) {

        items.forEachIndexed { index, item ->

            BottomMenuItems(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {

                selectedItemIndex = index
            }
        }
    }

}
*/




