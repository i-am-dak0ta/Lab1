package com.dak0ta.learnity.core.network.data.api.service

import com.dak0ta.learnity.core.network.data.api.dto.messenger.MessengerDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface MessengerService {

    @POST("messengers")
    suspend fun getMessengers(): List<MessengerDto>

    @GET("messengers/{id}")
    suspend fun getMessengerById(@Path("id") id: String): MessengerDto
}
