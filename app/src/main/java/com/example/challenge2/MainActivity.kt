package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import kotlinx.android.synthetic.main.activity_buy__login_.*
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), ViewPropertyAnimatorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ViewCompat.animate(arkadia).scaleX(0.8f).scaleY(0.8f).setListener(this@MainActivity).setDuration(3000)

        ViewCompat.animate(splash_image).scaleX(1.6f).scaleY(1.6f).setListener(this@MainActivity).setDuration(3000)
    }

    override fun onAnimationEnd(view: View?) {


        val intent = Intent(this@MainActivity, SplashActivity::class.java)
        startActivity(intent)
        finish() //讓他不要return


    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }


}
