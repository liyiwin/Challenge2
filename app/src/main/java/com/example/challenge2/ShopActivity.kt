package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shop.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ShopActivity : AppCompatActivity() {

    var itemList_arms =   mutableListOf<Model>()

    var itemList_foods =   mutableListOf<Model>()

    var itemList_special =   mutableListOf<Model>()



    private var mlayoutManager: GridLayoutManager? = GridLayoutManager(this.baseContext, 2)

   private var shopAdapter_food  = ShopAdapter_food(this@ShopActivity,mlayoutManager,itemList_foods)
    private var shopAdapter_arms = ShopAdapter_arms(this@ShopActivity,mlayoutManager,itemList_arms)
    private var shopAdapter_special = ShopAdapter_special(this@ShopActivity,mlayoutManager,itemList_special)

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

       val pref = sharedPreference_buy_login(this)

        val api_token = pref.get_api_token()

        val account =pref.get_account()

        val level = pref.get_level()?.toInt()?:0

        var level_int =   level



        if (level_int > 3  ){

              lock.visibility = View.INVISIBLE

              special.visibility = View.VISIBLE

              special.setEnabled(true)



          }






        shopAdapter_food.buy(object :ShopAdapter_food.buyItem_One{
            override fun item(id: Int) {

                val url = "http://35.234.60.173/api/sheepitem"
                val body = FormBody.Builder().add("account","$account").add("item_id","$id").add("stock","1").build()
                val request = Request.Builder().url(url).addHeader("Authorization","Bearer $api_token").post(body) .build()
                val call = OkHttpClient .newCall(request)
                call.enqueue(object: Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {

                        val myjson = response.body()?.string()!!.trim()

                        val code = response.code()

                        if (code == 201) {
                            val jasonObject = JSONObject(myjson)

                            runOnUiThread(object :Runnable{
                                override fun run() {
                                    Toast.makeText(this@ShopActivity, "購買成功！！！！" , Toast.LENGTH_SHORT).show()
                                }


                            })
                        }


                    }
                })

            }

        } )

        shopAdapter_arms.buy(object:ShopAdapter_arms.buyItem_Two{
            override fun item(id: Int) {

                val url = "http://35.234.60.173/api/sheepitem"
                val body = FormBody.Builder().add("account","$account").add("item_id","$id").add("stock","1").build()
                val request = Request.Builder().url(url).addHeader("Authorization","Bearer $api_token").post(body) .build()
                val call = OkHttpClient .newCall(request)
                call.enqueue(object: Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {

                        val myjson = response.body()?.string()!!.trim()

                        val code = response.code()

                        if (code == 201) {
                            val jasonObject = JSONObject(myjson)

                            runOnUiThread(object :Runnable{
                                override fun run() {
                                    Toast.makeText(this@ShopActivity, "購買成功！！！！" , Toast.LENGTH_SHORT).show()
                                }


                            })
                        }


                    }
                })

            }
        })


        shopAdapter_special.buy(object:ShopAdapter_special.buyItem_Three{
            override fun item(id: Int) {

                val url = "http://35.234.60.173/api/sheepitem"
                val body = FormBody.Builder().add("account","$account").add("item_id","$id").add("stock","1").build()
                val request = Request.Builder().url(url).addHeader("Authorization","Bearer $api_token").post(body) .build()
                val call = OkHttpClient .newCall(request)
                call.enqueue(object: Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {

                        val myjson = response.body()?.string()!!.trim()

                        val code = response.code()

                        if (code == 201) {
                            val jasonObject = JSONObject(myjson)

                            runOnUiThread(object :Runnable{
                                override fun run() {
                                    Toast.makeText(this@ShopActivity, "購買成功！！！！" , Toast.LENGTH_SHORT).show()
                                }


                            })
                        }


                    }
                })

            }

        })



        val url = "http://35.234.60.173/api/items/1"
        val request = Request.Builder().url(url).build()
        val call = OkHttpClient .newCall(request)
        call.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()

                if (code == 200) {
                    val jasonArray = JSONArray(myjson)

                    for ( i in 0 until jasonArray.length()){

                        val jasonObject  =  jasonArray.getJSONObject(i)

                     var id = jasonObject.getInt("id")

                     var sort_id =   jasonObject.getInt("sort_id")

                     var item_name=   jasonObject.getString("item_name")

                     var price=  jasonObject.getInt("price")

                     var stock=   jasonObject.getInt("stock")

                     var pic =   jasonObject.getString("pic")

                     var created_at=   jasonObject.getString("created_at")

                     var updated_at = jasonObject.getString("updated_at")

                        runOnUiThread(object : Runnable {
                            override fun run() {
                                itemList_foods.add(Model(id,sort_id,item_name,price,100,pic))

                                shopAdapter_food.notifyDataSetChanged()
                            }
                        })





                        //    var id :Int,
                        //    val sort_id:Int,
                        //    var name :String,
                        //    var price: String,
                        //    var stock:Int = 100,
                        //    var pic	:String

                        //    var mutableList: MutableList<Int>  = mutableListOf(666,777,888)
                        //        mutableList.addAll(mutableListOf(666,777,888))
                        //
                        //        println(mutableList)
                        //
                        // /**/  [666, 777, 888, 666, 777, 888]

                       val my_array = jasonObject.getJSONArray("sort")


                        val jasonObject2 = my_array.getJSONObject(0)

                        jasonObject2.getInt("id")

                        jasonObject2.getString("name")

                        jasonObject2.getString("created_at")

                        jasonObject2.getString("updated_at")



                    }

                    //[
                    //    {
                    //        "id": 2,
                    //        "sort_id": 1,
                    //        "item_name": "Adamm",
                    //        "price": 100,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:30:58",
                    //        "updated_at": "2019-11-26 01:19:14",
                    //        "sort": [
                    //            {
                    //                "id": 1,
                    //                "name": "糧食",
                    //                "created_at": "2019-11-26 11:59:20",
                    //                "updated_at": "2019-11-26 11:59:20"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 11,
                    //        "sort_id": 1,
                    //        "item_name": "ffffeee",
                    //        "price": 1,
                    //        "stock": null,
                    //        "pic": "http://35.234.60.173/storage/CKISDsogQnGwqK2hboguHbR9jFMrXrBZZ79KAEZh.jpeg",
                    //        "created_at": "2019-11-25 22:46:04",
                    //        "updated_at": "2019-11-25 22:46:04",
                    //        "sort": [
                    //            {
                    //                "id": 1,
                    //                "name": "糧食",
                    //                "created_at": "2019-11-26 11:59:20",
                    //                "updated_at": "2019-11-26 11:59:20"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 15,
                    //        "sort_id": 1,
                    //        "item_name": "ffffeeeg",
                    //        "price": 100,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/OmOunI8khwYX30iXLz7SLKIBxyGNbbTxwobMUe16.jpeg",
                    //        "created_at": "2019-11-25 22:48:55",
                    //        "updated_at": "2019-11-26 01:09:43",
                    //        "sort": [
                    //            {
                    //                "id": 1,
                    //                "name": "糧食",
                    //                "created_at": "2019-11-26 11:59:20",
                    //                "updated_at": "2019-11-26 11:59:20"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 30,
                    //        "sort_id": 1,
                    //        "item_name": "11",
                    //        "price": 1111,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-26 08:57:28",
                    //        "updated_at": "2019-11-26 09:00:05",
                    //        "sort": [
                    //            {
                    //                "id": 1,
                    //                "name": "糧食",
                    //                "created_at": "2019-11-26 11:59:20",
                    //                "updated_at": "2019-11-26 11:59:20"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 39,
                    //        "sort_id": 1,
                    //        "item_name": "2231",
                    //        "price": 100,
                    //        "stock": null,
                    //        "pic": null,
                    //        "created_at": "2019-11-26 13:13:39",
                    //        "updated_at": "2019-11-26 13:13:39",
                    //        "sort": [
                    //            {
                    //                "id": 1,
                    //                "name": "糧食",
                    //                "created_at": "2019-11-26 11:59:20",
                    //                "updated_at": "2019-11-26 11:59:20"
                    //            }
                    //        ]
                    //    }
                    //]
                }


            }
        })

        val url1 = "http://35.234.60.173/api/items/2"
        val request1 = Request.Builder().url(url1).build()
        val call1 = OkHttpClient .newCall(request1)
        call1.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {



            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()

                if (code == 200) {
                    val jasonArray = JSONArray(myjson)

                    for ( i in 0 until jasonArray.length()){

                        val jasonObject  =  jasonArray.getJSONObject(i)

                        var id = jasonObject.getInt("id")

                        var sort_id =   jasonObject.getInt("sort_id")

                        var item_name=   jasonObject.getString("item_name")

                        var price=  jasonObject.getInt("price")

                        var stock=   jasonObject.getInt("stock")

                        var pic =   jasonObject.getString("pic")

                        var created_at=   jasonObject.getString("created_at")

                        var updated_at = jasonObject.getString("updated_at")

                        runOnUiThread(object : Runnable {
                            override fun run() {
                                itemList_arms.add(Model(id,sort_id,item_name,price,100,pic))

                                shopAdapter_arms.notifyDataSetChanged()
                            }
                        })





                        //    var id :Int,
                        //    val sort_id:Int,
                        //    var name :String,
                        //    var price: String,
                        //    var stock:Int = 100,
                        //    var pic	:String

                        //    var mutableList: MutableList<Int>  = mutableListOf(666,777,888)
                        //        mutableList.addAll(mutableListOf(666,777,888))
                        //
                        //        println(mutableList)
                        //
                        // /**/  [666, 777, 888, 666, 777, 888]

                        val my_array = jasonObject.getJSONArray("sort")




                            val jasonObject2 = my_array.getJSONObject(0)

                            jasonObject2.getInt("id")

                            jasonObject2.getString("name")

                            jasonObject2.getString("created_at")

                            jasonObject2.getString("updated_at")





                    }

                   //[
                    //    {
                    //        "id": 1,
                    //        "sort_id": 2,
                    //        "item_name": "???",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:16:25",
                    //        "updated_at": "2019-11-26 01:11:14",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 4,
                    //        "sort_id": 2,
                    //        "item_name": "葡萄酒0",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:32:35",
                    //        "updated_at": "2019-11-26 01:19:02",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 5,
                    //        "sort_id": 2,
                    //        "item_name": "葡萄酒1",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:32:41",
                    //        "updated_at": "2019-11-26 01:19:06",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 6,
                    //        "sort_id": 2,
                    //        "item_name": "葡萄酒99",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:32:47",
                    //        "updated_at": "2019-11-26 01:18:20",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 7,
                    //        "sort_id": 2,
                    //        "item_name": "葡萄酒88",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:32:56",
                    //        "updated_at": "2019-11-26 01:19:10",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 41,
                    //        "sort_id": 2,
                    //        "item_name": "adds",
                    //        "price": 100,
                    //        "stock": 3,
                    //        "pic": null,
                    //        "created_at": "2019-11-26 13:26:00",
                    //        "updated_at": "2019-11-26 13:26:00",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 42,
                    //        "sort_id": 2,
                    //        "item_name": "113213",
                    //        "price": 100,
                    //        "stock": 2,
                    //        "pic": null,
                    //        "created_at": "2019-11-26 13:28:27",
                    //        "updated_at": "2019-11-26 13:28:27",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 43,
                    //        "sort_id": 2,
                    //        "item_name": "azcv",
                    //        "price": 200,
                    //        "stock": 0,
                    //        "pic": null,
                    //        "created_at": "2019-11-26 13:38:20",
                    //        "updated_at": "2019-11-26 13:38:20",
                    //        "sort": [
                    //            {
                    //                "id": 2,
                    //                "name": "武器",
                    //                "created_at": "2019-11-26 11:59:45",
                    //                "updated_at": "2019-11-26 11:59:45"
                    //            }
                    //        ]
                    //    }
                    //]
                }


            }
        })

        val url2 = "http://35.234.60.173/api/items/3"
        val request2 = Request.Builder().url(url2).build()
        val call2 = OkHttpClient .newCall(request2)
        call2.enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

                val myjson = response.body()?.string()!!.trim()

                val code = response.code()

                if (code == 200) {
                    val jasonArray = JSONArray(myjson)

                    for ( i in 0 until jasonArray.length()){

                        val jasonObject  =  jasonArray.getJSONObject(i)

                        var id = jasonObject.getInt("id")

                        var sort_id =   jasonObject.getInt("sort_id")

                        var item_name=   jasonObject.getString("item_name")

                        var price=  jasonObject.getInt("price")

                        var stock=   jasonObject.getInt("stock")

                        var pic =   jasonObject.getString("pic")

                        var created_at=   jasonObject.getString("created_at")

                        var updated_at = jasonObject.getString("updated_at")

                        runOnUiThread(object : Runnable {
                            override fun run() {
                                itemList_special.add(Model(id,sort_id,item_name,price,100,pic))

                                shopAdapter_special.notifyDataSetChanged()
                            }
                        })


                        //    var id :Int,
                        //    val sort_id:Int,
                        //    var name :String,
                        //    var price: String,
                        //    var stock:Int = 100,
                        //    var pic	:String

                        //    var mutableList: MutableList<Int>  = mutableListOf(666,777,888)
                        //        mutableList.addAll(mutableListOf(666,777,888))
                        //
                        //        println(mutableList)
                        //
                        // /**/  [666, 777, 888, 666, 777, 888]

                        val my_array = jasonObject.getJSONArray("sort")


                        val jasonObject2 = my_array.getJSONObject(0)

                        jasonObject2.getInt("id")

                        jasonObject2.getString("name")

                        jasonObject2.getString("created_at")

                        jasonObject2.getString("updated_at")



                    }

                   //[
                    //    {
                    //        "id": 3,
                    //        "sort_id": 3,
                    //        "item_name": "dddqw",
                    //        "price": 300,
                    //        "stock": 0,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-25 22:31:09",
                    //        "updated_at": "2019-11-26 01:18:57",
                    //        "sort": [
                    //            {
                    //                "id": 3,
                    //                "name": "特殊解鎖",
                    //                "created_at": "2019-11-26 11:59:56",
                    //                "updated_at": "2019-11-26 11:59:56"
                    //            }
                    //        ]
                    //    },
                    //    {
                    //        "id": 26,
                    //        "sort_id": 3,
                    //        "item_name": "wine",
                    //        "price": 500,
                    //        "stock": 999,
                    //        "pic": "http://35.234.60.173/storage/iLmB6f5mmaBzFfCsV6w7BC1gsgUZH92nB5CWGasc.png",
                    //        "created_at": "2019-11-26 05:56:00",
                    //        "updated_at": "2019-11-26 08:56:37",
                    //        "sort": [
                    //            {
                    //                "id": 3,
                    //                "name": "特殊解鎖",
                    //                "created_at": "2019-11-26 11:59:56",
                    //                "updated_at": "2019-11-26 11:59:56"
                    //            }
                    //        ]
                    //    }
                    //]
                }


            }
        })


        shop_recyclerview.layoutManager =mlayoutManager

        shop_recyclerview.adapter = shopAdapter_food


         food.setOnClickListener { shop_recyclerview.layoutManager =mlayoutManager

             shop_recyclerview.adapter = shopAdapter_food
         }

        arms.setOnClickListener {  shop_recyclerview.layoutManager =mlayoutManager

            shop_recyclerview.adapter = shopAdapter_arms
        }

            special.setOnClickListener { shop_recyclerview.layoutManager =mlayoutManager

                shop_recyclerview.adapter =  shopAdapter_special
            }


        list.setOnClickListener {


               mlayoutManager?.spanCount = 1

            list.visibility = View.INVISIBLE

            grid.visibility = View.VISIBLE

            list.setEnabled(false)

            grid.setEnabled(true)



        }

        grid.setOnClickListener {


            mlayoutManager?.spanCount = 2

            list.visibility = View.VISIBLE

            grid.visibility = View.INVISIBLE

            list.setEnabled(true)

            grid.setEnabled(false)




        }



//               shop_recyclerview.apply{
//                    layoutManager= LinearLayoutManager(context)
//                    adapter=shopAdapter
//
//                }
//
//
//
//        val url = "https://c9aa79d8.ngrok.io/api/vs/send"
//        val body = FormBody.Builder().add("name","$name").add("answer","$mine_point").build()
//        val request = Request.Builder().url(url).post(body) .build()
//        val call = OkHttpClient .newCall(request)
//        call.enqueue(object: Callback {
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//                val myjson = response.body()?.string()!!.trim()
//
//                val jasonObject = JSONObject(myjson)
//            }
//        })


    }
}
