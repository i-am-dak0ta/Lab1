package com.dak0ta.learnity.feature.authorization.data.repository

internal interface AuthorizationRepository {

    suspend fun login(input: String, password: String)
}
