package com.example.challenge2

import android.content.Context

class sharedPreference_sell_login(context: Context) {

    private val pref = context.getSharedPreferences("Total_sell_login", Context.MODE_PRIVATE)

    val editor = pref.edit()


    fun save_id_sell(id: String) {

        editor.putString("id", id).apply()

    }

    //xml 輸出

    fun get_id_sell(): String? {
        return pref.getString("id", "")

    }



    fun savename_sell(name: String) {

        editor.putString("name", name).apply()

    }

    //xml 輸出

    fun getname_sell(): String? {
        return pref.getString("name", "")

    }

    fun save_account_sell(account: String) {

        editor.putString("account", account).apply()

    }

    //xml 輸出

    fun get_account_sell(): String? {
        return pref.getString("account", "")

    }



    fun save_api_token_sell(api_token: String) {

        editor.putString("api_token", api_token).apply()

    }

    //xml 輸出

    fun get_api_token_sell(): String? {
        return pref.getString("api_token", "")

    }


    fun save_balance_sell(balance: String) {

        editor.putString("balance", balance).apply()

    }

    //xml 輸出

    fun get_balance_sell(): String? {
        return pref.getString("balance", "")

    }

    //{
    //    "msg": "狼先生，歡迎回到店裡",
    //    "now_flower": {
    //        "id": 1,
    //        "camp_name": "Arcadia",
    //        "account": "SheepChild",
    //        "api_token": "J6IjNMbnKU",
    //        "balance": 1000000,
    //        "created_at": "2019-11-25 22:10:27",
    //        "updated_at": "2019-11-26 16:11:34"
    //    }
    //}

    fun delete() {

        editor.clear().commit()
    }

}