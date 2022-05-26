package com.task.appiness.data.network


import com.task.appiness.data.network.response.MyHierarchy
import retrofit2.http.GET

interface AuthApi : BaseApi {


    @GET(ApiConstants.GET_HIERARCHY)
    suspend fun getHierarchy(): MyHierarchy

}