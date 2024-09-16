package com.example.fitz.dataclass

data class Workouts(
    val workoutName: String,
    val vidUrl: String,
    val steps: List<String>,
    val reps: Int,
    val sets: Int
)
