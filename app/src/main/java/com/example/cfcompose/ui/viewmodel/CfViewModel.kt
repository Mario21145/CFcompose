package com.example.cfcompose.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.cfcompose.R
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.data.Data
import com.example.cfcompose.data.Data.months
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CfViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CfUiState())
    val uiState: StateFlow<CfUiState> = _uiState.asStateFlow()

    val data = Data


    fun setSurname(surname: String) {
        _uiState.update { currentState ->
            currentState.copy(
                surname = surname
            )
        }
    }

    fun setName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }


    fun setYear(year: String) {
        _uiState.update { currentState ->
            currentState.copy(
                year = year
            )
        }
    }

    fun setMonth(month: String) {
        _uiState.update { currentState ->
            currentState.copy(
                month = month
            )
        }
    }

    fun setDay(day: String) {
        _uiState.update { currentState ->
            currentState.copy(
                day = day
            )
        }
    }

    fun setSex(sex: String) {
        _uiState.update { currentState ->
            currentState.copy(
                sex = sex
            )
        }
    }


    fun setCity(city: String) {
        _uiState.update { currentState ->
            currentState.copy(
                city = city
            )
        }
    }


    fun clearAll() {
        _uiState.update { currentState ->
            currentState.copy(
                liveCf = "",
                surname = "",
                name = "",
                year = "",
                month = "",
                day = "",
                sex = "",
                stateSex = 0,
                city = ""
            )
        }
    }


    fun calcSurname(newValue: String): String {
        var upperNewValue = mutableListOf<Char>()
        var part = ""

        upperNewValue.clear()
        upperNewValue.addAll(newValue.map { it.uppercaseChar() })

        if (upperNewValue.isNotEmpty()) {
            setSurname(newValue)
            part = calcConsonants(upperNewValue)
        }
        return part
    }

    fun calcName(newValue: String): String {
        var upperNewValue = mutableListOf<Char>()
        var part = ""

        upperNewValue.clear()
        upperNewValue.addAll(newValue.map { it.uppercaseChar() })

        if (upperNewValue.isNotEmpty()) {
            setName(newValue)
            part = calcConsonants(upperNewValue)
        }
        return part
    }


    fun calcDate(year: Int, month: Int, day: Int): String {

        var part = ""

        if (year.toString().isNotEmpty() && month.toString()
                .isNotEmpty() && day.toString().isNotEmpty()
        ) {

            val yearResult = calcYear(year.toString())
            val monthResult = calcMonth(month)
            val dayResult = calcDay(day.toString())

            setYear(year.toString())
            val monthName = when (month) {
                in 1..12 -> months[month - 1]
                else -> null
            }
            if (monthName != null) {
                setMonth(monthName)
            }
            setDay(dayResult)




            part = yearResult + monthResult + dayResult
        }

        return part
    }

    fun calcSex(isMenSelected: Boolean, isWomenSelected: Boolean): String {
        var part = ""
        val state = _uiState.value.stateSex

        if (_uiState.value.day.isNotEmpty()) {
            if (isMenSelected) {
                if (_uiState.value.stateSex == 0) {
                    setSex("men")
                    updateCF(9..10)
                    val calcDay = _uiState.value.day.toInt().minus(40)
                    part = if (calcDay < 0) {
                        _uiState.value.day
                    } else {
                        calcDay.toString().padStart(2, '0')
                    }
                    setDay(part)
                }

                if (state == 0) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            stateSex = 1
                        )
                    }
                }
            }

            if (isWomenSelected) {
                if (_uiState.value.stateSex == 1) {
                    setSex("women")
                    updateCF(9..10)
                    val calcDay = _uiState.value.day.toInt().plus(40)
                    part = calcDay.toString()
                    setDay(part)
                }

                if (state == 1) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            stateSex = 0
                        )
                    }
                }
            }
        }

        return part
    }


    fun calcCity(city: String): String {
        when (city) {
            data.cities[0] -> {
                return ""
            }

            data.cities[1] -> {
                return "B759"
            }

            data.cities[2] -> {
                return "A064"
            }

            data.cities[3] -> {
                return "D789"
            }

            data.cities[4] -> {
                return "A512"
            }

            data.cities[5] -> {
                return "F839"
            }

            data.cities[6] -> {
                return "A024"
            }

            else -> {}
        }
        return ""
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

    fun updateCF(rangePosition: IntRange) {

        val convertedLiveCf: MutableList<Char> = _uiState.value.liveCf.toMutableList()
        if (rangePosition.first in 0 until convertedLiveCf.size && rangePosition.last in 0 until convertedLiveCf.size) {
            for (i in rangePosition) {
                convertedLiveCf[i] = ' '
            }
            convertedLiveCf.removeAll { it == ' ' }

            _uiState.update { currentState ->
                currentState.copy(
                    liveCf = convertedLiveCf.joinToString("")
                )
            }

//            _uiState.value.liveCf = convertedLiveCf.joinToString("")
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

            7 -> {
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

    fun calcLastLetter(cf: String): String {
        if (cf.length == 15) {
            var sum = 0
            for ((index, char) in cf.withIndex()) {
                if ((index + 1) % 2 == 0) {
                    sum += data.evenValues[char] ?: 0
                } else {
                    sum += data.oddValues[char] ?: 0
                }
            }

            val controlValue = sum % 26
            val controlChar = data.letterValues.entries.first { it.value == controlValue }.key

            return controlChar.toString()
        }
        return ""
    }

    fun checkDayFinal() : Int{

        if(_uiState.value.day.isNotEmpty()){
            if(_uiState.value.stateSex == 0) {
                return _uiState.value.day.toInt().minus(40)
            } else {
                return _uiState.value.day.toInt()
            }
        }
        return 0

    }
    
}