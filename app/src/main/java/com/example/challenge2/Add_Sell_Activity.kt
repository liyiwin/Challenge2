package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add__sell_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Add_Sell_Activity : AppCompatActivity() {

    //item_name
    //sort_id
    //price
    //stock

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    var sort_id:Int =0

    var item_name:String =""

    var price:Int =0

    var stock:Int =0

    var sort_id_array= arrayListOf(1,2,3)

    var item_name_array= arrayListOf("meat", "rice", "wine", "cereal", "shield","halberd","sword","blad","socrates","troy_horse")

    var price_array= arrayListOf(200, 50, 100, 400, 300,500)

    var stock_array= arrayListOf(10,20,30)







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__sell_)

        val pref = sharedPreference_sell_login(this@Add_Sell_Activity)

        var api:String =  "${pref.get_api_token_sell()}"


val adapter_sort_id = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sort_id_array)
spinner_sort_id.adapter = adapter_sort_id

        spinner_sort_id.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                 sort_id =sort_id_array[pos]




                Toast.makeText(this@Add_Sell_Activity, "你選的是" + sort_id_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



val adapter_item_name = ArrayAdapter(this,  android.R.layout.simple_spinner_dropdown_item,item_name_array)
spinner_item_name.adapter = adapter_item_name

        spinner_item_name.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                 item_name = item_name_array[pos]



                Toast.makeText(this@Add_Sell_Activity, "你選的是" + item_name_array[pos], Toast.LENGTH_SHORT).show()

            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

val adapter_price = ArrayAdapter(this,  android.R.layout.simple_spinner_dropdown_item,price_array)
spinner_price.adapter = adapter_price


        spinner_price.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                price =  price_array[pos]



                Toast.makeText(this@Add_Sell_Activity, "你選的是" + price_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

val adapter_stock = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,stock_array)
spinner_stock.adapter = adapter_stock

        spinner_stock.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                 stock = stock_array[pos]

                Toast.makeText(this@Add_Sell_Activity, "你選的是" + stock_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



        add.setOnClickListener {

             val url = "http://35.234.60.173/api/wolf/items"
                    val body = FormBody.Builder().add("sort_id","$sort_id").add("item_name",item_name).add("price","$price").add("stock","$stock").build()

                    //item_name	string
                    //sort_id	integer
                    //sort_name	string
                    //price	integer
                    //stock	integer
                    val request = Request.Builder().url(url).addHeader("Authorization","Bearer $api").post(body) .build()
                    val call = OkHttpClient .newCall(request)
                    call.enqueue(object: Callback {
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {

                            val myjson = response.body()?.string()!!.trim()

                            val code = response.code()

                            if (code == 201) {

                                val data = JSONObject(myjson).getJSONObject("data")


                                runOnUiThread(object :Runnable{
                                    override fun run() {
                                        Toast.makeText(this@Add_Sell_Activity, "新增成功！！！！" , Toast.LENGTH_SHORT).show()
                                    }


                                })


                            }
                        }
                    })

        }









    }
}

//"items": [
//        {
//            "id": 70,
//            "sort_id": 1,
//            "item_name": "糧草",
//            "price": 100,
//            "stock": 340,
//            "pic": "http://35.234.60.173/storage/YvNRakVbxo5vaZr8BwJ3UgyaQDvajZIWGcHuEHvH.png",
//            "created_at": "2019-11-26 19:48:03",
//            "updated_at": "2019-11-27 13:04:40"
//        },
//        {
//            "id": 82,
//            "sort_id": 1,
//            "item_name": "aaa",
//            "price": 200,
//            "stock": 30,
//            "pic": "http://35.234.60.173/storage/ptR3zJX86nbmPQXu3yvMUK5Xcmz8AygpPuSPQFO6.png",
//            "created_at": "2019-11-27 09:31:21",
//            "updated_at": "2019-11-27 13:04:08"
//        },
//        {
//            "id": 69,
//            "sort_id": 3,
//            "item_name": "木馬屠城術",
//            "price": 8000,
//            "stock": 1,
//            "pic": "http://35.234.60.173/storage/BqhUp1dLAh8aJuS2uu9oe484yy9YzILbuTPixdBO.png",
//            "created_at": "2019-11-26 19:47:16",
//            "updated_at": "2019-11-27 12:45:46"
//        },
//        {
//            "id": 81,
//            "sort_id": 1,
//            "item_name": "Bala",
//            "price": 860,
//            "stock": 29,
//            "pic": "http://35.234.60.173/storage/demdDneNp4qHLJmjkoqNiDkjCFEuVwbA2khmVBxN.png",
//            "created_at": "2019-11-27 09:23:41",
//            "updated_at": "2019-11-27 11:06:20"
//        },
//        {
//            "id": 72,
//            "sort_id": 1,
//            "item_name": "帶骨肉",
//            "price": 300,
//            "stock": 14,
//            "pic": "http://35.234.60.173/storage/IagIuoFODNS4PfThtZcnu8ts5PqcSCS2PpACptsu.png",
//            "created_at": "2019-11-26 19:49:35",
//            "updated_at": "2019-11-27 11:06:17"
//        },
//        {
//            "id": 73,
//            "sort_id": 1,
//            "item_name": "稻穀",
//            "price": 150,
//            "stock": 20,
//            "pic": "http://35.234.60.173/storage/demdDneNp4qHLJmjkoqNiDkjCFEuVwbA2khmVBxN.png",
//            "created_at": "2019-11-26 19:50:12",
//            "updated_at": "2019-11-26 22:17:24"
//        },
//        {
//            "id": 67,
//            "sort_id": 3,
//            "item_name": "傭兵",
//            "price": 2000,
//            "stock": 50,
//            "pic": "http://35.234.60.173/storage/FarnldvDyOtWNB6pfKpR1xo43SrZUwADX5BLhfsx.png",
//            "created_at": "2019-11-26 19:45:50",
//            "updated_at": "2019-11-26 21:04:17"
//        },
//        {
//            "id": 68,
//            "sort_id": 3,
//            "item_name": "老巫師",
//            "price": 5000,
//            "stock": 5,
//            "pic": "http://35.234.60.173/storage/MNBInv5yDqwtEZkKJdNPwGBfGGZ8gj4hFe3VvFcB.png",
//            "created_at": "2019-11-26 19:46:40",
//            "updated_at": "2019-11-26 20:57:06"
//        },
//        {
//            "id": 78,
//            "sort_id": 2,
//            "item_name": "銀龍槍",
//            "price": 1800,
//            "stock": 7,
//            "pic": "http://35.234.60.173/storage/or6HZlYv5q2WiMFNnBTTQiJNr6GtRaz6bL8hlp2P.png",
//            "created_at": "2019-11-26 19:55:27",
//            "updated_at": "2019-11-26 19:55:27"
//        },
//        {
//            "id": 77,
//            "sort_id": 2,
//            "item_name": "方天畫戟",
//            "price": 1300,
//            "stock": 6,
//            "pic": "http://35.234.60.173/storage/D25aPwQx95L4fOggOA63A2TmlTSgNqwEisGWWtsO.png",
//            "created_at": "2019-11-26 19:54:40",
//            "updated_at": "2019-11-26 19:54:40"
//        },
//        {
//            "id": 75,
//            "sort_id": 2,
//            "item_name": "名刀不知火",
//            "price": 1500,
//            "stock": 3,
//            "pic": "http://35.234.60.173/storage/ioy5lLqbxZiHPSvZnvfdPqm9vfQhmpNMpKHskUAu.png",
//            "created_at": "2019-11-26 19:51:21",
//            "updated_at": "2019-11-26 19:54:09"
//        },
//        {
//            "id": 76,
//            "sort_id": 2,
//            "item_name": "秋水",
//            "price": 2000,
//            "stock": 2,
//            "pic": "http://35.234.60.173/storage/6XnBiaeWQcq9OoEk1bL0TxvBjsPjZZX5xrL0u4KP.png",
//            "created_at": "2019-11-26 19:53:49",
//            "updated_at": "2019-11-26 19:53:49"
//        },
//        {
//            "id": 74,
//            "sort_id": 2,
//            "item_name": "盾牌",
//            "price": 500,
//            "stock": 5,
//            "pic": "http://35.234.60.173/storage/JkcPjuXoSzprYyvi2hOZFeONObVQqKOAUBqEOCfx.png",
//            "created_at": "2019-11-26 19:50:51",
//            "updated_at": "2019-11-26 19:50:51"
//        },
//        {
//            "id": 71,
//            "sort_id": 1,
//            "item_name": "美酒",
//            "price": 300,
//            "stock": 20,
//            "pic": "http://35.234.60.173/storage/4sXaN1qtEiDDPFXq6n3xtudTjWY9JAi5dIhupVvo.png",
//            "created_at": "2019-11-26 19:48:53",
//            "updated_at": "2019-11-26 19:48:53"
//        }
//    ]
//}










