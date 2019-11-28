package com.example.challenge2

import android.content.Context

class sharedPreference_buy_registered(context: Context) {


    private val pref = context.getSharedPreferences("Total_buy_registered", Context.MODE_PRIVATE)

    val editor = pref.edit()


    fun savename2(name: String) {

        editor.putString("name", name).apply()

    }

    //xml 輸出

    fun getname2(): String? {
        return pref.getString("name", "")

    }

    fun save_account2(account: String) {

        editor.putString("account", account).apply()

    }

    //xml 輸出

    fun get_account2(): String? {
        return pref.getString("account", "")

    }


    fun save_api_token2(api_token: String) {

        editor.putString("name", api_token).apply()

    }

    //xml 輸出

    fun get_api_token2(): String? {
        return pref.getString("api_token", "")

    }


    fun delete() {

        editor.clear().commit()
    }

}