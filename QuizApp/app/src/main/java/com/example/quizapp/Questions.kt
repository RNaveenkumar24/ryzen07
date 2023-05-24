package com.example.quizapp

data class Questions (
    val id:Int,
    val question:String,
    val image:Int,
    val OptionOne :String,
    val OptionTwo :String,
    val OptionThree :String,
    val OptionFour :String,
    val CorrectOption : Int
    )