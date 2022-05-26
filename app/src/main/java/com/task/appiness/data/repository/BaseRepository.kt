package com.task.appiness.data.repository

import com.task.appiness.data.network.SafeApiCall
import com.task.appiness.data.network.BaseApi

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {
}