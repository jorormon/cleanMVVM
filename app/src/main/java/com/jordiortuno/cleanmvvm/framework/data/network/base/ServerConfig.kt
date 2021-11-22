package com.jordiortuno.cleanmvvm.framework.data.network.base

import com.jordiortuno.cleanmvvm.framework.MyApp

object ServerConfig {

    private const val PRODUCTION_SERVER = ""
    private const val DEVELOPMENT_SERVER = ""
    private const val TEST_SERVER = ""
    const val URN_API = "/api/"
    fun getBaseUrl(): String {
        return when (MyApp.appStatus) {
            MyApp.Environment.PRODUCTION -> {
                PRODUCTION_SERVER
            }
            MyApp.Environment.DEVELOPMENT -> {
                DEVELOPMENT_SERVER
            }
            MyApp.Environment.TEST -> {
                TEST_SERVER
            }
        }
    }
    /**
     * Build url to call a specific server depending on app_status
     */
    fun buildUrlAPI(path: String): String {
        var urlPath: String = ""
        when (MyApp.appStatus) {
            MyApp.Environment.DEVELOPMENT -> {
                urlPath = DEVELOPMENT_SERVER + URN_API + path
            }
            MyApp.Environment.PRODUCTION -> {
                urlPath = PRODUCTION_SERVER + URN_API + path
            }
            MyApp.Environment.TEST -> {
                urlPath = TEST_SERVER + URN_API + path
            }
        }
        return urlPath
    }
}