package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buy__login_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Buy_Login_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy__login_)

        val pref = sharedPreference_buy_login(this@Buy_Login_Activity)


        buy_registered_button.setOnClickListener {

            val intent = Intent(this@Buy_Login_Activity,Buy_Registered_Activity::class.java)

            startActivity(intent) }



        //account	SheepTwo	string
        //password	12345678	string

        buy_login_button.setOnClickListener {

            var account= buy_account_editText.text.toString()

            var password= buy_password_editText.text.toString()


             val url = "http://35.234.60.173/api/login"
            val body = FormBody.Builder().add("account","$account").add("password","$password").build()
             val request = Request.Builder().url(url).post(body) .build()
            val call = OkHttpClient .newCall(request)
             call.enqueue(object: Callback {
             override fun onFailure(call: Call, e: IOException) {

                               }

             override fun onResponse(call: Call, response: Response) {

                 val myjson = response.body()?.string()!!.trim()

                 val code = response.code()


                   if (code == 200) {
                       //{
//    "msg": "可愛的小綿羊，歡迎光臨，今天想要買點什麼呢？",
//    "lv": 0,
//    "now_sheep": {
//        "id": 7,
//        "name": "小綿羊",
//        "account": "sheepzero",
//        "balance": 56800,
//        "api_token": "5lgMKrX2Ds",
//        "created_at": "2019-11-26 10:47:07",
//        "updated_at": "2019-11-27 22:12:06",
//        "score": 200,
//        "lucky": null
//    },
//    "last_lv": 1800
//}


                       var level = JSONObject(myjson).getInt("lv").toString()
                       pref.save_level("$level")

                       var last_lv=  JSONObject(myjson).getInt("last_lv").toString()

                       pref.save_last_lv("$last_lv")


                       val jasonObject = JSONObject(myjson).getJSONObject("now_sheep")

                       var my_account_api_token =  jasonObject.getString("api_token")

                       pref.save_api_token("$my_account_api_token")

                     var id = jasonObject.getInt("id").toString()

                       pref.save_id("$id")

                     var my_name=  jasonObject.getString("name")

                      pref.savename("$my_name")

                     var my_account =  jasonObject.getString("account")
                       pref.save_account("$my_account")

                       var balance =jasonObject.getInt("balance").toString()

                       pref.save_balance("$balance")


                       var score =  jasonObject.getInt("score").toString()

                       pref.save_score("$score")




                       runOnUiThread(object : Runnable {
                           override fun run() {

                               Toast.makeText(this@Buy_Login_Activity, "登入成功", Toast.LENGTH_LONG).show()

                           }


                       })

                 }


              }




        })





    }
}}

