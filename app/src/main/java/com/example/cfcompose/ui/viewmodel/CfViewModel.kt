package com.example.cfcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.data.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CfViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CfUiState())
    val uiState: StateFlow<CfUiState> = _uiState.asStateFlow()

    val data = Data


    fun setCF(part: String) {
        if (_uiState.value.liveCf == "") {
            _uiState.update { currentState ->
                currentState.copy(
                    liveCf = part
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    liveCf = _uiState.value.liveCf + part
                )
            }
        }
    }

    fun updateCF(rangePosition: IntRange) {

        val convertedLiveCf: MutableList<Char> = _uiState.value.liveCf.toMutableList()
        if (rangePosition.first in 0 until convertedLiveCf.size && rangePosition.last in 0 until convertedLiveCf.size) {
            for (i in rangePosition) {
                convertedLiveCf[i] = ' '
            }
            convertedLiveCf.removeAll { it == ' ' }
            _uiState.value.liveCf = convertedLiveCf.joinToString("")
        }
    }









    fun calcConsonants(name: List<Char>): String {
        var char_result = mutableListOf<Char>()
        val c = mutableListOf<Char>()
        val v = mutableListOf<Char>()

        for (letterName in name) {
            if (data.consonants.contains(letterName)) {
                c.add(letterName)
                if (c.size == 3) {
                    char_result = c
                    break
                }
            }

            if (data.vocals.contains(letterName)) {
                v.add(letterName)
            }

        }

        val maxLenghtChar = 3

        if (v.isNotEmpty()) {
            if (c.size != maxLenghtChar) {
                when (c.size) {
                    0 -> char_result = c.plus(v).toMutableList()
                    1 -> {
                        var i = 0
                        while (i < 1) {
                            char_result = c.plus(v[i]).toMutableList()
                            i++
                        }
                    }

                    2 -> char_result = c.plus(v[0]).toMutableList()
                }
            }
        }
        return char_result.joinToString("")
    }

    fun calcYear(date: String): String {
        if (date.length == 4) {
            return date.takeLast(2)
        } else {
            return ""
        }
    }

    fun calcDay(day: String): String {
        if (day.length == 2) {
            return day
        } else {
            return day.padStart(2, '0')
        }
    }

    fun calcMonth(month: Int): String {
        when (month) {

            0 -> {
                return "A"
            }

            1 -> {
                return "B"
            }

            2 -> {
                return "C"
            }

            3 -> {
                return "D"
            }

            4 -> {
                return "E"
            }

            5 -> {
                return "H"
            }

            6 -> {
                return "L"
            }

            7-> {
                return "M"
            }

            8 -> {
                return "P"
            }

            9 -> {
                return "R"
            }

            10 -> {
                return "S"
            }

            11 -> {
                return "T"
            }

            else -> {
                return ""
            }
        }
    }








}