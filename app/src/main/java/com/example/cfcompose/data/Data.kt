package com.example.cfcompose.data

import java.util.Locale

object Data {


    val language = Locale.getDefault().language


    val cities: List<String> by lazy {
        if (language == "it") {
            listOf(
                "Scegli una città" , "cardito", "afragola", "frattamaggiore", "aversa", "napoli", "acerra"
            )
        } else {
            listOf(
                "Choose a city" , "cardito", "afragola", "frattamaggiore", "aversa", "napoli", "acerra"
            )
        }
    }

    val months: List<String> by lazy {
        if (language == "it") {
          listOf(
                "Gennaio", "Febbraio", "Marzo", "Aprile",
                "Maggio", "Giugno", "Luglio", "Agosto",
                "Settembre", "Ottobre", "Novembre", "Dicembre", "mese"
            )
        } else {
            listOf(
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December", "month"
            )
        }
    }




    val consonants = listOf(
        'B',
        'C',
        'D',
        'F',
        'G',
        'H',
        'J',
        'K',
        'L',
        'M',
        'N',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'V',
        'W',
        'X',
        'Y',
        'Z'
    )

    val vocals = listOf('A', 'E', 'I', 'O', 'U')

    //Dispari
    val oddValues = mapOf(
        '0' to 1, '1' to 0, '2' to 5, '3' to 7, '4' to 9, '5' to 13,
        '6' to 15, '7' to 17, '8' to 19, '9' to 21, 'A' to 1, 'B' to 0,
        'C' to 5, 'D' to 7, 'E' to 9, 'F' to 13, 'G' to 15, 'H' to 17,
        'I' to 19, 'J' to 21, 'K' to 2, 'L' to 4, 'M' to 18, 'N' to 20,
        'O' to 11, 'P' to 3, 'Q' to 6, 'R' to 8, 'S' to 12, 'T' to 14,
        'U' to 16, 'V' to 10, 'W' to 22, 'X' to 25, 'Y' to 24, 'Z' to 23
    )

    //Pari
    val evenValues = mapOf(
        '0' to 0, '1' to 1, '2' to 2, '3' to 3, '4' to 4, '5' to 5,
        '6' to 6, '7' to 7, '8' to 8, '9' to 9, 'A' to 0, 'B' to 1,
        'C' to 2, 'D' to 3, 'E' to 4, 'F' to 5, 'G' to 6, 'H' to 7,
        'I' to 8, 'J' to 9, 'K' to 10, 'L' to 11, 'M' to 12, 'N' to 13,
        'O' to 14, 'P' to 15, 'Q' to 16, 'R' to 17, 'S' to 18, 'T' to 19,
        'U' to 20, 'V' to 21, 'W' to 22, 'X' to 23, 'Y' to 24, 'Z' to 25
    )

    //Risultati
    val letterValues = mapOf(
        'A' to 0, 'B' to 1, 'C' to 2, 'D' to 3, 'E' to 4, 'F' to 5,
        'G' to 6, 'H' to 7, 'I' to 8, 'J' to 9, 'K' to 10, 'L' to 11,
        'M' to 12, 'N' to 13, 'O' to 14, 'P' to 15, 'Q' to 16, 'R' to 17,
        'S' to 18, 'T' to 19, 'U' to 20, 'V' to 21, 'W' to 22, 'X' to 23,
        'Y' to 24, 'Z' to 25
    )





}