package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recoder.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RecoderActivity : AppCompatActivity() {

    var itemList_history =   mutableListOf<Model2>()

  private  var recoder_adapter =  RecoderAdapter(this@RecoderActivity,itemList_history)

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoder)

        val pref = sharedPreference_buy_login(this@RecoderActivity)

        history_recyclerview.layoutManager = LinearLayoutManager(this@RecoderActivity)

        history_recyclerview.adapter = recoder_adapter

        var id = pref.get_id()

        var mytoken =pref.get_api_token()

        var level =pref.get_level()


        recoder_name.setText("${pref.getname()}")


        val url = "http://35.234.60.173/api/sheep/allbuy/$id"
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $mytoken").build()
        val call = OkHttpClient.newCall(request)
        call.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()


                if (code == 200) {

                val jasonObject = JSONObject(myjson)



                val SheepAllBuy=   jasonObject. getJSONArray("SheepAllBuy")

               if(SheepAllBuy.length() > 0 ){

                   for( i in 0 until SheepAllBuy.length()  ){

                       var jsonObject = SheepAllBuy.getJSONObject(i)

                     var   my_id=  jsonObject.getInt("id")
                     var   my_item_id=jsonObject.getInt("item_id")
                       var my_price =jsonObject.getString("price")
                       var my_stock =jsonObject.getString("stock")
                       var my_total=jsonObject.getString("total")
                       var my_item_name =jsonObject.getString("item_name")
                       var  my_sort_id =jsonObject.getInt("sort_id")
                       var  my_pic=jsonObject.getString("pic")
                                           // "SheepAllBuy": [
                                           //        {
                                           //            "id": 35,
                                           //            "item_id": 70,
                                           //            "price": "100",
                                           //            "stock": "1",
                                           //            "total": "100",
                                           //            "item_name": "糧草",
                                           //            "sort_id": 1,
                                           //            "pic": "http://35.234.60.173/storage/YvNRakVbxo5vaZr8BwJ3UgyaQDvajZIWGcHuEHvH.png"
                                           //        } ]

                       runOnUiThread(object :Runnable{
                           override fun run() {

                               itemList_history.add(Model2(my_id, my_item_id,my_price,my_stock,my_total,my_item_name,my_sort_id, my_pic))

                               recoder_adapter.notifyDataSetChanged()


                           }


                       })


                   }


               }




//{
//    "msg": "買家所有購買的物品",
//    "sheep": {
//        "id": 7,
//        "name": "小綿羊",
//        "account": "sheepzero",
//        "balance": 26800,
//        "api_token": "J4YAFXynbU",
//        "created_at": "2019-11-26 10:47:07",
//        "updated_at": "2019-11-27 11:19:11",
//        "score": 200
//    },
//    "SheepAllBuy": []
//}



                }
            }
        })







    }
}
