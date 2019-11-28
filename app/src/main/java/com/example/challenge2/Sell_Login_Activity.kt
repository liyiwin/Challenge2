package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sell__login_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Sell_Login_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell__login_)

        val pref = sharedPreference_sell_login(this@Sell_Login_Activity)


        sell_login_button.setOnClickListener {


            var account= sell_account_editText.text.toString()

            var password= sell_password_editText.text.toString()

            val url = "http://35.234.60.173/api/wolf/login"
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

                       val jasonObject = JSONObject(myjson).getJSONObject("now_flower")

                       var my_account = jasonObject.getString("account")

                        pref.save_account_sell("$my_account")

                        var my_balance =  jasonObject.getInt("balance").toString()

                        pref.save_balance_sell("$my_balance")

                       var my_api_token= jasonObject.getString("api_token")

                        pref.save_api_token_sell("$my_api_token")



                        //{
                        //    "msg": "狼先生，歡迎回到店裡",
                        //    "now_flower": {
                        //        "id": 1,
                        //        "camp_name": "Arcadia",
                        //        "account": "SheepChild",
                        //        "api_token": "J6IjNMbnKU",
                        //        "balance": 1000000,
                        //        "created_at": "2019-11-25 22:10:27",
                        //        "updated_at": "2019-11-26 16:11:34"
                        //    }
                        //}




                        //{
                        //    "msg": "狼先生，歡迎回到店裡",
                        //    "now_flower": {
                        //        "id": 1,
                        //        "camp_name": "Arcadia",
                        //        "account": "SheepChild",
                        //        "api_token": "RnrX4UE3SY",
                        //        "balance": 1000000,
                        //        "created_at": "2019-11-25 14:11:43",
                        //        "updated_at": "2019-11-25 14:19:47"
                        //    }
                        //}

                        runOnUiThread(object : Runnable {
                            override fun run() {

                                Toast.makeText(this@Sell_Login_Activity, "商家登入成功", Toast.LENGTH_LONG).show()

                            }


                        })




                    }
                }
            })
        }


    }
}
