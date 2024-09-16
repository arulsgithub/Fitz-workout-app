import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.fitz.ui.theme.ButtonBlue
import com.example.fitz.ui.theme.DeepBlue
import com.example.fitz.ui.components.CheckBoxes
import kotlinx.coroutines.launch

@Composable
fun WorkoutScreen(
    navController: NavController,
    workout: String,
    url: String,
    steps: List<String> // Add steps as a parameter
) {
    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
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
                    Player.STATE_BUFFERING -> println("Video is buffering...")
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> println("Video playback ended.")
                    Player.STATE_IDLE -> println("Player is idle.")
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    // Replace '+' with a space in each step string
    val cleanedSteps = steps.map { step ->
        step.replace("+", " ")
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
            text = workout,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical = 30.dp)
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
                    factory = { ctx -> PlayerView(ctx).apply { this.player = player } },
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
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
        }

        Text(
            text = "Preparations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )

        // Display preparation steps dynamically
        cleanedSteps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }

        CheckBoxes("10", 4) // Assuming CheckBoxes is another composable
    }
}
