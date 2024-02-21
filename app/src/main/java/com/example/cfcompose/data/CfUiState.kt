package com.example.cfcompose.data

data class CfUiState (

    var liveCf : String = "",

    var surname : String = "",
    var name : String = "",
    var year : String = "",
    var month : String = "",
    var day  : String = "",

    var sex : String = "",
    var stateSex : Int = 0,

    var stateStepSurname : Boolean = false,
    var stateStepName : Boolean = false,
    var stateStepDate : Boolean = false,
    var stateStepSex : Boolean = false,
    var stateStepCity : Boolean = false,
    var stateStepRecap : Boolean = false,

    var stateStep : Boolean = false,

    var city : String = ""


)