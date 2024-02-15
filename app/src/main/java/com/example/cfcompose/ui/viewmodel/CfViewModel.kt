package com.example.cfcompose.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cfcompose.Data.CfUiState
import com.example.cfcompose.Data.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CfViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CfUiState(liveCf = "" , ))
    val uiState: StateFlow<CfUiState> = _uiState.asStateFlow()


    val data = Data

    fun calcConsonants(name: List<Char>) : String {
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

        if (!v.isEmpty()) {
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
        val result = char_result.joinToString("")
        return result
    }



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







}