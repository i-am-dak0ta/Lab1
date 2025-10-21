package com.dak0ta.learnity.core.network.data.network

import com.dak0ta.learnity.core.network.domain.auth.AccessTokenRefresher
import com.dak0ta.learnity.core.network.domain.auth.TokenResetter
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthAuthenticator @Inject constructor(
    private val refreshTokenManager: AccessTokenRefresher,
    private val tokenResetter: TokenResetter,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val request: Request? = runBlocking {
            if (responseCount(response) >= 2) {
                null
            } else {
                val newToken = refreshTokenManager.refreshAccessToken()
                if (newToken != null) {
                    response.request.newBuilder()
                        .header("Authorization", "Bearer $newToken")
                        .build()
                } else {
                    tokenResetter.resetToken()
                    null
                }
            }
        }
        return request
    }

    private fun responseCount(response: Response): Int {
        var res = response.priorResponse
        var count = 1
        while (res != null) {
            count++
            res = res.priorResponse
        }
        return count
    }
}
