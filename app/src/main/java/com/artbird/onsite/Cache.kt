package com.artbird.onsite

import android.content.Context
//import android.util.LruCache

//class Cache {
//    private val cache = LruCache<String, String>(1024)
//
//    public fun put(key: String, value: String) {
//        cache.put(key, value)
//    }
//
//    public fun get(key: String): String {
//        return cache.get(key)
//    }
//}


class SharedPreferencesHelper(context: Context) {
    private val preferences = context.getSharedPreferences("jwt_token", Context.MODE_PRIVATE)
    private val TOKEN_KEY = "token_key"

    fun saveToken(token: String) {
        preferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, null)
    }
}