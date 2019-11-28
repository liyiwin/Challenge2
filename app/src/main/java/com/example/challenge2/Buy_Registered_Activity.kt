package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buy__registered_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Buy_Registered_Activity : AppCompatActivity() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy__registered_)

       val pref = sharedPreference_buy_registered(this@Buy_Registered_Activity)


        buy_registered_button2.setOnClickListener {


            var name = buy_name_registered_editText.text.toString()

            var account = buy_account_registered_editText.text.toString()

            var  password = buy_password_registered_editText.text.toString()

//            name	第二隻羊	string
//            account	SheepTwo	string
//            password	12345678	string

             val url = "http://35.234.60.173/api/register"
            val body = FormBody.Builder().add("name","$name").add("account","$account").add("password","$password").build()
             val request = Request.Builder().url(url).post(body) .build()
            val call = OkHttpClient .newCall(request)
             call.enqueue(object: Callback {
             override fun onFailure(call: Call, e: IOException) {

                               }

             override fun onResponse(call: Call, response: Response) {

             val myjson = response.body()?.string()!!.trim()

                 val code = response.code()

                 if (code == 200){

                     //{
                     //    "msg": "可愛的肥羊，註冊成功",
                     //    "create_date": {
                     //        "name": "第二隻羊",
                     //        "account": "SheepTwo",
                     //        "balance": 5000,
                     //        "api_token": "SjRqYw9Yeu",
                     //        "updated_at": "2019-11-25 14:45:02",
                     //        "created_at": "2019-11-25 14:45:02",
                     //        "id": 3
                     //    }
                     //}

                  val jasonObject = JSONObject(myjson).getJSONObject("create_date")

                   var my_account =jasonObject.getString("account")

                     pref.save_account2("$my_account")

                   var my_api_token = jasonObject.getString("api_token")

                     pref.save_api_token2("$my_api_token")

                    var my_name =   jasonObject.getString("name")

                     pref.savename2("$ my_name")



                     //{
                     //    "msg": "可愛的肥羊，註冊成功",
                     //    "create_date": {
                     //        "name": "第二隻羊",
                     //        "account": "SheepTwo",
                     //        "balance": 5000,
                     //        "api_token": "SjRqYw9Yeu",
                     //        "updated_at": "2019-11-25 14:45:02",
                     //        "created_at": "2019-11-25 14:45:02",
                     //        "id": 3
                     //    }
                     //}

                     runOnUiThread(object :Runnable{
                         override fun run() {

                             Toast.makeText(this@Buy_Registered_Activity,"註冊成功", Toast.LENGTH_LONG).show()

                         }


                     })



                 }


                               }
                           })



        }






    }
}
