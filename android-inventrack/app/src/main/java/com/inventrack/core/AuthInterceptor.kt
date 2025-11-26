package com.inventrack.core

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {
    private val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // Attach access token if available
        val token = runBlocking { dataStoreManager.getToken() }
        var request = original.newBuilder().apply {
            if (!token.isNullOrEmpty()) addHeader("Authorization", "Bearer $token")
        }.build()

        var response = chain.proceed(request)

        if (response.code == 401) {
            response.close()
            // Try refresh token flow synchronously
            val refreshed = tryRefreshToken()
            if (refreshed != null) {
                // retry original request with new token
                val newReq = original.newBuilder().apply {
                    addHeader("Authorization", "Bearer $refreshed")
                }.build()
                response = chain.proceed(newReq)
            } else {
                // nothing to do, token invalid - clear stored credentials
                runBlocking { dataStoreManager.clear() }
            }
        }
        return response
    }

    private fun tryRefreshToken(): String? {
        return runBlocking {
            try {
                val refreshTokenStored = dataStoreManager.getRefreshToken()
                val accessTokenFallback = dataStoreManager.getToken()
                val tokenToUse = refreshTokenStored ?: accessTokenFallback
                if (tokenToUse.isNullOrEmpty()) return@runBlocking null

                val client = OkHttpClient.Builder().build()
                val mediaType = "application/json; charset=utf-8".toMediaType()
                val bodyJson = gson.toJson(mapOf("refresh_token" to tokenToUse))
                val body = bodyJson.toRequestBody(mediaType)
                val req = Request.Builder()
                    .url(Constants.BASE_URL.trimEnd('/') + "/auth/refresh")
                    .post(body)
                    .build()
                val resp = client.newCall(req).execute()
                if (!resp.isSuccessful) {
                    resp.close()
                    return@runBlocking null
                }
                val respBody = resp.body?.string()
                resp.close()
                if (respBody == null) return@runBlocking null
                val map: Map<String, Any> = gson.fromJson(respBody, Map::class.java)
                val access = map["access_token"] as? String
                val refreshNew = map["refresh_token"] as? String
                if (!access.isNullOrEmpty()) {
                    dataStoreManager.saveToken(access)
                    if (!refreshNew.isNullOrEmpty()) dataStoreManager.saveRefreshToken(refreshNew)
                    return@runBlocking access
                }
                return@runBlocking null
            } catch (e: Exception) {
                Log.e("AuthInterceptor", "refresh failed", e)
                null
            }
        }
    }
}
