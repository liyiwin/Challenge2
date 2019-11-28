package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete__sell_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Delete_Sell_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete__sell_)


        val pref = sharedPreference_sell_login(this@Delete_Sell_Activity)

        var api:String =  "${pref.get_api_token_sell()}"

        //        {
        //            "id": 72,
        //            "sort_id": 1,
        //            "item_name": "帶骨肉",
        //            "price": 300,
        //            "stock": 14,
        //            "pic": "http://35.234.60.173/storage/IagIuoFODNS4PfThtZcnu8ts5PqcSCS2PpACptsu.png",
        //            "created_at": "2019-11-26 19:49:35",
        //            "updated_at": "2019-11-27 11:06:17"
        //        }


                delete.setOnClickListener {


             var select_my_id=   delete_editText.text.toString().toInt()


            val url1 = "http://35.234.60.173/api/wolf/items/$select_my_id"
            val body = FormBody.Builder().build()

            //item_name	string
            //sort_id	integer
            //sort_name	string
            //price	integer
            //stock	integer
            val request1 = Request.Builder().url(url1).addHeader("Authorization","Bearer $api").delete(body) .build()
            val call1 = OkHttpClient .newCall(request1)
            call1.enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val myjson1 = response.body()?.string()!!.trim()

                    val code1 = response.code()

                    if (code1 == 200) {

                        runOnUiThread(object :Runnable{
                            override fun run() {
                                Toast.makeText(this@Delete_Sell_Activity, "刪除成功！！！！" , Toast.LENGTH_SHORT).show()
                            }


                        })


                    }
                }
            })

        }
    }
}
