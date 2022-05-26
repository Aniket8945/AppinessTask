package com.task.appiness.data.repository

import com.task.appiness.data.network.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) : BaseRepository(api) {

    suspend fun getHierarchy() = safeApiCall {
        api.getHierarchy()
    }

}