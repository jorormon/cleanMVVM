package com.jordiortuno.cleanmvvm.framework.data.network

import android.content.Context
import android.content.SharedPreferences

/**
 * Manage tokens saved on sharedPreferences
 * Initialized at the constructor of the App
 */
class Provider {

    companion object{
        private lateinit var sharedPreferences: SharedPreferences
        @JvmStatic
        fun initialize(context: Context){
            sharedPreferences = context.applicationContext.getSharedPreferences(
                SharedPreferencesFacade.PREF_NAME,
                Context.MODE_PRIVATE
            )
        }

        /**
         * Get refresh token from sharedPreferences
         * @return String?
         */
        fun getRefreshToken():String?{
            return sharedPreferences.getString(SharedPreferencesFacade.USER_REFRESH_TOKEN, "")
        }

        /**
         * Save refresh token on sharedPreferences
         * @param value String
         */
        fun setRefreshToken(value:String){
            val editor = sharedPreferences.edit()
            editor.putString(SharedPreferencesFacade.USER_REFRESH_TOKEN, value)
            editor.apply()
        }

        /**
         * Get access token from sharedPreferences
         * @return String?
         */
        fun getAccessToken():String?{
            return sharedPreferences.getString(SharedPreferencesFacade.USER_TOKEN, "")
        }

        /**
         * Save access token on sharedPreferences
         * @param value String
         */
        fun setAccessToken(value: String){
            val editor = sharedPreferences.edit()
            editor.putString(SharedPreferencesFacade.USER_TOKEN, value)
            editor.apply()
        }

        fun saveAccessTokens(accessToken:String,refreshToken:String){
            setAccessToken(accessToken)
            setRefreshToken(refreshToken)
        }

    }

}