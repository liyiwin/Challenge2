package com.example.challenge2

import android.content.Context

class sharedPreference_buy_login(context: Context) {

    private val pref = context.getSharedPreferences("Total_buy_login", Context.MODE_PRIVATE)

    val editor = pref.edit()


    fun save_id(id: String) {

        editor.putString("id", id).apply()

    }


    //xml 輸出

    fun get_id(): String? {
        return pref.getString("id", "0")

    }


    fun savename(name: String) {

        editor.putString("name", name).apply()

    }


    //xml 輸出

    fun getname(): String? {
        return pref.getString("name", "未登入")

    }

    fun save_account(account: String) {

        editor.putString("account", account).apply()

    }

    //xml 輸出

    fun get_account(): String? {
        return pref.getString("account", "")

    }

    //    fun save_balance(balance: String) {
    //
    //        editor.putString("balance", balance).apply()
    //
    //    }
    //
    //    //xml 輸出
    //
    //    fun get_balance(): String? {
    //        return pref.getString("balance", "")
    //
    //    }


    fun save_balance(balance: String) {

        editor.putString("balance", balance).apply()

    }



    //xml 輸出

    fun get_balance(): String? {
        return pref.getString("balance", "0")}



        fun save_api_token(api_token: String) {

        editor.putString("api_token", api_token).apply()

    }



    //xml 輸出

    fun get_api_token(): String? {
        return pref.getString("api_token", "")

    }


        fun save_score(score: String) {

            editor.putString("score",score).apply()

        }

        //xml 輸出

        fun get_score(): String? {
            return pref.getString("score", "0")

        }


        fun save_item(item: String) {

            editor.putString("item", item).apply()

        }

        //xml 輸出

    fun get_item(): String? {
            return pref.getString("item", "")

        }


    fun save_level(level: String) {

        editor.putString("level", level).apply()

    }

    //xml 輸出

    fun get_level(): String? {
        return pref.getString("level" ,"0")

    }


        fun save_last_lv(last_lv: String) {

            editor.putString("last_lv", last_lv).apply()

        }

        //xml 輸出

        fun get_last_lvl(): String? {
            return pref.getString("last_lv" ,"0")

        }



    fun delete() {

        editor.clear().commit()
    }

}