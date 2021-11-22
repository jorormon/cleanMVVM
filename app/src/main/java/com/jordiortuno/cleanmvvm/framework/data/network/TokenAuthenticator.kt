package com.jordiortuno.cleanmvvm.framework.data.network

import com.jordiortuno.cleanmvvm.framework.data.network.api.TokenRefreshApi
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.framework.data.network.base.SafeApiCall
import com.jordiortuno.cleanmvvm.framework.data.network.common.toRequestBody
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.json.JSONObject

//Dependencia al provider
//Pasar el contexto por el constructor y crear UserPreferences class cuando Inyector de dependencias
class TokenAuthenticator(
    private val tokenApi: TokenRefreshApi
) : Authenticator,SafeApiCall {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is ResultWrapper.Success -> {
                    Provider.saveAccessTokens(
                        tokenResponse.value.accessToken,
                        tokenResponse.value.refreshToken
                    )
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenResponse.value.accessToken}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getUpdatedToken(): ResultWrapper<LoginResponse> {
        val refreshToken = Provider.getRefreshToken()
        return safeApiCall {
            val body = JSONObject()
            body.put("refresh_token",refreshToken)

            tokenApi.refreshAccessToken(body.toRequestBody()) }
    }

}