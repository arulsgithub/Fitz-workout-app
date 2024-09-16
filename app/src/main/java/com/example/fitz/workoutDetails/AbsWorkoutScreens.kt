package com.example.fitz.workoutDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.fitz.ui.components.CheckBoxes
import com.example.fitz.ui.theme.ButtonBlue
import com.example.fitz.ui.theme.DeepBlue
import kotlinx.coroutines.launch


@Composable
fun LegRaise(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5001.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preperations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}


@Composable
fun MountainClimber(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5025.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preperations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}
@Composable
fun Crunch(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5002.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preparations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}

@Composable
fun BicycleCrunch(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5006.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preparations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}
@Composable
fun VUp(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5010.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preperations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}
@Composable
fun FullPlank(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5034.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preperations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

            )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}
