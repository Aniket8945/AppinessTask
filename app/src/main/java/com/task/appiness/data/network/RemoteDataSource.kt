package com.task.appiness.data.network

import android.content.Context
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {
    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(getRetrofitClient(null))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
            .writeTimeout(5,TimeUnit.MINUTES)
            .readTimeout(5,TimeUnit.MINUTES)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                authenticator?.let { client.authenticator(it) }
//                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
//                }
            }.build()
    }
}