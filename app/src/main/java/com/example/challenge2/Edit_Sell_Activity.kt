package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit__sell_.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Edit_Sell_Activity : AppCompatActivity() {

    //select id (uri use)

//item_name	string
//sort_id	integer
//price	integer
//stock	integer
//pic	string(null)




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
        setContentView(R.layout.activity_edit__sell_)

        val pref = sharedPreference_sell_login(this@Edit_Sell_Activity)

        var api:String =  "${pref.get_api_token_sell()}"







        val adapter_sort_id = ArrayAdapter(this@Edit_Sell_Activity,android.R.layout.simple_spinner_dropdown_item, sort_id_array)

        //ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sort_id_array)
        spinner_sort_id_edit.adapter = adapter_sort_id

        spinner_sort_id_edit.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                sort_id =sort_id_array[pos]




                Toast.makeText(this@Edit_Sell_Activity, "你選的是" + sort_id_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }






        val adapter_item_name = ArrayAdapter(this@Edit_Sell_Activity,  android.R.layout.simple_spinner_dropdown_item,item_name_array)
        spinner_item_name_edit.adapter = adapter_item_name

        spinner_item_name_edit.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                item_name = item_name_array[pos]



                Toast.makeText(this@Edit_Sell_Activity, "你選的是" + item_name_array[pos], Toast.LENGTH_SHORT).show()

            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }




        val adapter_price = ArrayAdapter(this@Edit_Sell_Activity,  android.R.layout.simple_spinner_dropdown_item,price_array)
        spinner_price_edit.adapter = adapter_price


        spinner_price_edit.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                price =  price_array[pos]



                Toast.makeText(this@Edit_Sell_Activity, "你選的是" + price_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val adapter_stock = ArrayAdapter(this@Edit_Sell_Activity, android.R.layout.simple_spinner_dropdown_item,stock_array)
        spinner_stock_edit.adapter = adapter_stock

        spinner_stock_edit.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

                stock = stock_array[pos]

                Toast.makeText(this@Edit_Sell_Activity, "你選的是" + stock_array[pos], Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        edit.setOnClickListener {

            val select_my_id = id_editText.text.toString().toInt()

            val url2 = "http://35.234.60.173/api/wolf/items/$select_my_id"

            val body = FormBody.Builder()
                .add("sort_id","$sort_id")
                .add("item_name",item_name)
                .add("price","$price")
                .add("stock","$stock")
                .add("pic","http://35.234.60.173/storage/IagIuoFODNS4PfThtZcnu8ts5PqcSCS2PpACptsu.png").build()

            //item_name	string
            //sort_id	integer
            //price	integer
            //stock	integer
            //pic null
            val request1 = Request.Builder().url(url2).addHeader("Authorization","Bearer $api").put(body) .build()
            val call1 = OkHttpClient .newCall(request1)
            call1.enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val myjson = response.body()?.string()!!.trim()

                    val code = response.code()

                    if (code == 200) {

                        runOnUiThread(object :Runnable{
                            override fun run() {
                                Toast.makeText(this@Edit_Sell_Activity, "修改成功！！！！" , Toast.LENGTH_SHORT).show()
                            }


                        })


                    }
                }
            })


        }
    }
}
