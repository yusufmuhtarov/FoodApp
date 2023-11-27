package com.example.foodApp.models

import java.io.Serializable

data class ModelFood(
    val foodName: String,
    val foodPrice: String,
) : Serializable
