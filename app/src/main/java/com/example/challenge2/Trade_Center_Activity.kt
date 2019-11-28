package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_trade__center_.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class Trade_Center_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade__center_)

        val pref = sharedPreference_sell_login(this@Trade_Center_Activity)

        val mytoken = pref.get_api_token_sell()


        val url = "http://35.234.60.173/api/wolfitem"
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $mytoken").build()
        val call = OkHttpClient .newCall(request)
        call.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {


            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()

                if (code == 200) {

                val jasonObject = JSONObject(myjson)

                 var msg=  jasonObject.getString("msg")

                  var lv=  jasonObject.getInt("lv")


                  var all_stock =  jasonObject.getInt("all_stock").toString()




                  var all_total =  jasonObject.getInt(("all_total")).toString()




                    runOnUiThread(object : Runnable {
                        override fun run() {

                            total_number.setText("Stock:  $all_stock ")

                            total_money.setText("$: $all_total ")

                        }


                    })

                    //{
                    //    "msg": "狼先生，請再多努力賺錢",
                    //    "lv": 1,
                    //    "all_stock": 149,
                    //    "all_total": 15000
                    //}



                }


            }
        })

    }
}
