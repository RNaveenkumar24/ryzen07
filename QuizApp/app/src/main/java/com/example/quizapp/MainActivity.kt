package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.ButtonBarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<ButtonBarLayout>(R.id.btn_start)
  val editName = findViewById<AppCompatEditText>(R.id.et_name)
   btnStart.setOnClickListener{
       if(editName.text.toString().isEmpty()){
           Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
       }
       else{
           val intent =Intent(this,QuizquestionsActivity::class.java)
           startActivity(intent)
           finish()
       }
    }

    }
}
