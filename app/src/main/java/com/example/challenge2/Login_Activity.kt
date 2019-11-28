package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_buy__login_.*
import kotlinx.android.synthetic.main.activity_login_.*
import kotlinx.android.synthetic.main.activity_login_.view.*
import kotlinx.android.synthetic.main.activity_main.*

class Login_Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val pref = sharedPreference_buy_login(this@Login_Activity)

        buyer_name.setText("${pref.getname()}")

        my_money.setText("$ ${pref.get_balance()}")

        seller_level.setText("Level: ${pref.get_level()}")

        val level = "${pref.get_level()}"




       // val pref = sharedPreference(this)


        shop.setOnClickListener {


                val intent = Intent(this@Login_Activity,ShopActivity::class.java)

                startActivity(intent)


        }

        documentary.setOnClickListener {
            val intent = Intent(this@Login_Activity,RecoderActivity::class.java)

            startActivity(intent)

        }

        log_out_btn2.setOnClickListener {

            pref.delete()

        }

        personal_documentary.setOnClickListener {

            val intent = Intent(this@Login_Activity,Personal_Documentary_Activity::class.java)

            startActivity(intent)


        }





    }
}
