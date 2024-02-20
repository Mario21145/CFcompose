package com.example.cfcompose.model

import androidx.annotation.StringRes

data class StepModel (
    @StringRes val title : Int,
    var completed : Boolean,
)
