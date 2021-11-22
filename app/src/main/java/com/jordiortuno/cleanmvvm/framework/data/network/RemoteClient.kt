package com.jordiortuno.cleanmvvm.framework.data.network

import com.jordiortuno.cleanmvvm.BuildConfig
import com.jordiortuno.cleanmvvm.framework.data.network.api.TokenRefreshApi
import com.jordiortuno.cleanmvvm.framework.data.network.base.RetryCallAdapterFactory
import com.jordiortuno.cleanmvvm.framework.data.network.base.ServerConfig
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RemoteClient {

    fun <Api> buildApi(
        api: Class<Api>,
    ): Api {
        val authenticator = TokenAuthenticator(buildTokenApi())
        return Retrofit.Builder()
            .baseUrl(ServerConfig.getBaseUrl())
            .client(getRetrofitClient(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RetryCallAdapterFactory.create())
            .build()
            .create(api)
    }
    fun <Api> buildAuthApi(
        api: Class<Api>,
    ): Api {
        val authenticator = TokenAuthenticator(buildTokenApi())
        return Retrofit.Builder()
            .baseUrl(ServerConfig.getBaseUrl())
            .client(getRetrofitClientAuth())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RetryCallAdapterFactory.create())
            .build()
            .create(api)
    }

    private fun buildTokenApi(): TokenRefreshApi {
        return Retrofit.Builder()
            .baseUrl(ServerConfig.getBaseUrl())
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TokenRefreshApi::class.java)
    }





    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                if(authenticator == null) manageHeadersLogin(chain)
                else manageHeaders(chain)
            }.also { client ->
                authenticator?.let { client.authenticator(it) }
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
    private fun getRetrofitClientAuth(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                manageHeadersLogin(chain)
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }

    private fun manageHeaders(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
            it.addHeader("Content-Type", "application/json")
            it.addHeader("Authorization", "Bearer " + addAccessToken())
        }.build()
        return chain.proceed(request)
    }
    private fun manageHeadersLogin(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
            it.addHeader("Content-Type", "application/json")
        }.build()
        return chain.proceed(request)
    }
     private fun addAccessToken():String?{
       val  x =  Provider.getAccessToken()
         Provider.setAccessToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhtWQiOiIyIiwianRpIjoiNzNkY2Y0YjYxYmFlMTMxNzA1OWY2YzYxNmVhMWU4MGM0ZDdmZThlYWVmNDNlZDJkZTU2MTYxYjY4MTkyNzU1NjM3YTI2MjZjMTc2OWRiZTciLCJpYXQiOjE2Mjg0OTkxNDYuNTI5MTgxLCJuYmYiOjE2Mjg0OTkxNDYuNTI5MTg2LCJleHAiOjE2MjkxMDM5NDYuNDg5NTI1LCJzdWIiOiI1Iiwic2NvcGVzIjpbIioiXX0.lmsgkm0mn23s7-rL3oT8d2JPFLOuoBkNLgRctfG1CF74uDSo-XuYJAHJRXDXyeKCGCOIq2AmuFVCY3R-lUUeQvVqGBfzUez0sNICapU57h0_p13v-uARjAiOJIujklH2KNoy7yVDhsEsUxtcULpLgYrtnA0qD9-yVizQDGMOg_B_6xog_8UwKvvNQ8p1KYVrwHmGfVytsWsixrT2cMeu0O4pVNVMz0YCVQ7h0r7WC-km6YZ3KyKI0cjfgXdfD0Q_nDD-ZMY9-YPpvEo2Uvty8FZ_rdr9CBS5M5IASDUV40MYjvKFsydsL6Oxxhmgdr-Ejuk4RPzEGAEC7Z0WIcOgAQYLBJNHO4nEMgA2K8NDxBGw5gZxDiCCgFnkpmEgyMnDaCXazmS6o2wXw8lXPtwEwZP0gCr0BLSreufU0YiAKvCN58GwbONoDIHPExWjLfHypaW10EoRzGSCLkl3FfNwoN8f9nj2XcC94LSEHqzNT90EtCKvEaXHhL9sqiAmOYMUpzg92yfpk5b4044Sn6V5X31YbDsdgcvpgxKIdBMzOy0GfNi8fOSJmgKNLd6zDQiir4Gjt1rbBBXF8amZEhpkHl0DuNPWCfYevxozVHo3phnYWj5eNQc9K0tsQgY_bEZbKu7-WSa7OJafQe9ZAZ_OdTun8s33tA1JAVWUXI8RWeA")
         Provider.setRefreshToken("b")
         return x
    }
}


