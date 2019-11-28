package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {



     override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login_)




            btn_sell.setOnClickListener {

                val intent = Intent(this@SplashActivity,Sell_Login_Activity::class.java)

                startActivity(intent)

            }

            btn_buy.setOnClickListener {

                val intent = Intent(this@SplashActivity,Buy_Login_Activity::class.java)

                startActivity(intent)

            }

            please_enter.setOnClickListener {

                val intent = Intent(this@SplashActivity, Login_Activity::class.java)

                startActivity(intent)

            }



            manage.setOnClickListener {

                val intent = Intent(this@SplashActivity, Main_View_Activity::class.java)

                startActivity(intent)


            }
        }
}
