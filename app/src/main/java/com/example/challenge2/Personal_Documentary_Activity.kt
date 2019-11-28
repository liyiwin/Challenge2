package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_personal__documentary_.*

class Personal_Documentary_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal__documentary_)

     var pref = sharedPreference_buy_login(this@Personal_Documentary_Activity)

    val level =  pref .get_level()

    val last_level =pref.get_last_lvl()

    val score = pref.get_score()!!.toInt()

        now_point.setText("目前分數:$score")

        last_point.setText("距離下一等級：$last_level")

        now_level.setText("目前等級：$level")


     progressBar.setMax(40000)

        my_progressBar(score)


    }


    fun my_progressBar(point :Int){

        progressBar.incrementProgressBy(point)


    }



}
