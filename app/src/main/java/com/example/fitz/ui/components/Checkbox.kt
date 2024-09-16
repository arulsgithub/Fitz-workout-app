package com.example.fitz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitz.R
import com.example.fitz.classes.ToggleInfo

@Composable
fun CheckBoxes(
    count: String,
    initialSetCount: Int
) {
    var setCount by remember { mutableStateOf(initialSetCount) }
    var completed by remember { mutableStateOf(0) }

    val checkBoxes = remember {
        mutableStateListOf<ToggleInfo>().apply {
            repeat(initialSetCount) {
                add(
                    ToggleInfo(
                        set = "Set ${it + 1}",
                        reps = "$count Reps",
                        isChecked = false
                    )
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF06164C)) // Use the screen color
    ) {
        checkBoxes.forEachIndexed { index, info ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1C3170),
                                Color(0xFF864042)
                            )
                        )
                    )
            ) {
                Text(
                    text = info.set,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = info.reps,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Checkbox(
                    checked = info.isChecked,
                    onCheckedChange = { isChecked ->
                        checkBoxes[index] = info.copy(isChecked = isChecked)
                        completed = checkBoxes.count { it.isChecked }
                    }
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color.Gray)
                .clickable {
                    setCount += 1
                    checkBoxes.add(
                        ToggleInfo(
                            set = "Set ${checkBoxes.size + 1}",
                            reps = "$count Reps",
                            isChecked = false
                        )
                    )
                }
        ) {
            Icon(
                painter = painterResource(id =  R.drawable.baseline_add_24),
                contentDescription = "Add", // Use a relevant description
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(
                    if (completed == setCount) Color(0xFF4CEB6E) else Color(0xFF6A706B)
                )
                .clickable {
                }
        ) {
            Text(
                text = "Finished",
                color = if (completed == setCount) Color.White else Color(0xFFA4F1F9),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}