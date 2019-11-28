package com.example.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_sell__page_.*
import kotlinx.android.synthetic.main.alerdialog.view.*

class Sell_Page_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell__page_)

        val pref = sharedPreference_sell_login(this@Sell_Page_Activity)

        seller_name.setText("${pref.get_account_sell()}")

        edit_sell_btn.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.alerdialog, null)

            mDialogView.add_page.setOnClickListener {

                 val intent = Intent(this@Sell_Page_Activity,Add_Sell_Activity::class.java)

                    startActivity(intent)
            }

            mDialogView.edit_page.setOnClickListener {

           val intent = Intent(this@Sell_Page_Activity,Edit_Sell_Activity::class.java)

                    startActivity(intent)

            }

            mDialogView.delete_page.setOnClickListener {

                 val intent = Intent(this@Sell_Page_Activity,Delete_Sell_Activity::class.java)

                    startActivity(intent)


            }



            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .create()
                .show()



//            val list_item = arrayOf("新增","修改","刪除")
//            val mBuilder = AlertDialog.Builder(this)
//                .setTitle("Login ")
//                .setSingleChoiceItems("新增"){dialog, which ->
//
//                    val intent = Intent(this@Sell_Page_Activity,Add_Sell_Activity::class.java)
//
//                    startActivity(intent)
//
//                }
//                .setSingleChoiceItems("修改"){dialog, which ->
//
//                    val intent = Intent(this@Sell_Page_Activity,Edit_Sell_Activity::class.java)
//
//                    startActivity(intent)
//
//                }
//                .setSingleChoiceItems("刪除"){dialog, which ->
//
//                    val intent = Intent(this@Sell_Page_Activity,Delete_Sell_Activity::class.java)
//
//                    startActivity(intent)
//
//                }
//                .show()
//                . create()


        }

        watch_sell_btn.setOnClickListener {

            val intent = Intent(this@Sell_Page_Activity,Watch_Sell_Activity::class.java)

            startActivity(intent)

        }



        log_out_btn.setOnClickListener {

            pref.delete()

        }




    }
}
