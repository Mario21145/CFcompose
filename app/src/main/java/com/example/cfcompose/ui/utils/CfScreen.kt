package com.example.cfcompose.ui.utils

import androidx.annotation.StringRes
import com.example.cfcompose.R

enum class CfScreen(@StringRes val title: Int) {
    Start(title = R.string.start),
    Surname(title = R.string.surname),
    Name(title = R.string.name),
    Date(title = R.string.date),
    Sex(title = R.string.sex),
    City(title = R.string.city),
    Recap(title = R.string.recap),
}