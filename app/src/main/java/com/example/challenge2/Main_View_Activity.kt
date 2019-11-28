package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main__view_.*
import kotlinx.android.synthetic.main.activity_trade__center_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Main_View_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__view_)

        val pref = sharedPreference_sell_login(this@Main_View_Activity )

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

                    var lv=  jasonObject.getInt("lv").toString()

                    var all_stock =  jasonObject.getInt("all_stock")

                    var all_total =  jasonObject.getInt("all_total")


                    //{
                    //    "msg": "狼先生，請再多努力賺錢",
                    //    "lv": 1,
                    //    "all_stock": 149,
                    //    "all_total": 15000
                    //}



                    var i = all_stock * all_total



                    runOnUiThread(object : Runnable {
                        override fun run() {



                            level.setText("Level $lv")



                            if (i >= 10000){

                                castle.setImageResource(R.drawable.five)

                            }

                            if(i >= 5000){

                                castle.setImageResource(R.drawable.four)

                            }

                            if(i >= 3000){

                                castle.setImageResource(R.drawable.two)

                            }

                            else if(i>1000){

                                castle.setImageResource(R.drawable.three)

                            }

                            else{

                                castle.setImageResource(R.drawable.one)

                            }


                          }


                    })









                }


            }
        })

        welcome.setOnClickListener {

            val intent = Intent(this@Main_View_Activity,Trade_Center_Activity::class.java)

            startActivity(intent)

        }

        manager_manage.setOnClickListener {

            val intent = Intent(this@Main_View_Activity,Sell_Page_Activity::class.java)

            startActivity(intent)
        }
    }
}
