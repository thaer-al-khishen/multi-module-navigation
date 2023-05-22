package com.thaer.core.repository

import android.content.Context
import com.thaer.core.ApiServiceHelper
import com.thaer.core.di.NetworkModule

open class BaseRepository {

    protected val apiService = ApiServiceHelper.provideApiService()

}