package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class QuizquestionsActivity : AppCompatActivity() ,View.OnClickListener{

    val optionOne =findViewById<TextView>(R.id.tv_optionone)
    val optionTwo =findViewById<TextView>(R.id.tv_optiontwo)
    val optionThree =findViewById<TextView>(R.id.tv_optionthree)
    val optionFour =findViewById<TextView>(R.id.tv_optionfour)
    val submit:AppCompatButton = findViewById(R.id.bt_submit)
    var correctanswer =0

    private var myCurrentPosition = 1
    private var myQuestionList:ArrayList<Questions> ?= null
    private var mySelectedOption =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizquestions)
        val myQuestionsList=Constants.getQuestions()
        setQuestion()
        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        submit.setOnClickListener(this)
    }
    /** question setter
     *
     *
     *
     *
     * **/
    private fun setQuestion(){
        defaultOptionsView()
        val question = myQuestionList!!.get(myCurrentPosition)
        if(myCurrentPosition==myQuestionList!!.size)
        {
            submit.text ="FINISH"
        }
        else{
            submit.text ="SUBMIT"
        }
        val progressBar = findViewById<ProgressBar>(R.id.pb)
        progressBar.progress=myCurrentPosition
        val tvProgressBar = findViewById<TextView>(R.id.tv_pb)
        tvProgressBar.text = "$myCurrentPosition"+"/"+progressBar.max
        val tvQuestion = findViewById<TextView>(R.id.tv_question)
        tvQuestion.text =question!!.question
        optionOne.text=question!!.OptionOne
        optionTwo.text=question!!.OptionTwo
        optionThree.text=question!!.OptionThree
        optionFour.text=question!!.OptionFour
    }

    /** default option setter
     *
     *
     * **/

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,optionOne)
        options.add(1,optionTwo)
        options.add(2,optionThree)
        options.add(3,optionFour)
        for (option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }


    }
/** onclick function */
    override fun onClick(tv: View?) {
when(tv?.id) {
    R.id.tv_optionone -> {
        selectedOptionView(optionOne, 1)
    }
    R.id.tv_optiontwo -> {
        selectedOptionView(optionTwo, 2)
    }
    R.id.tv_optionthree -> {
        selectedOptionView(optionThree, 3)
    }
    R.id.tv_optionfour -> {
        selectedOptionView(optionFour, 4)
    }
    R.id.bt_submit -> {
        if (mySelectedOption == 0) {
            myCurrentPosition++
            when {
                myCurrentPosition <= myQuestionList!!.size -> {
                    setQuestion()

                }

                else -> {
                    Toast.makeText(
                        this,
                        "You have succesfully completed the quiz",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
        else {
            val question = myQuestionList?.get(myCurrentPosition - 1)
            if (question!!.CorrectOption!=mySelectedOption){
                answerView(mySelectedOption,R.drawable.wrong_option_border_bg)
            }
            else{
                correctanswer++
            }

            answerView(question!!.CorrectOption,R.drawable.correct_option_border_bg)
            if(myCurrentPosition==myQuestionList!!.size)
            {
                submit.text="FINISH"
            }
            else{
                submit.text ="GO TO NEXT QUESTION"
            }
            myCurrentPosition++
            mySelectedOption=0
    }

}}}

    /**selected option view setter
     *
     *
     *
     * */
  private   fun selectedOptionView(tv:TextView,SelectedOptionNum:Int){
        defaultOptionsView()
        mySelectedOption=SelectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg)
    }
    /* answer view setter i.e green for correct question red for wrong question
    *
    *
    * */

    private fun answerView(answer:Int,drawableView:Int){
        when(answer)
        {
            1->{
                optionOne.background= ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                optionTwo.background= ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                optionThree.background= ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                optionFour.background= ContextCompat.getDrawable(this,drawableView)
            }

        }
    }


}
