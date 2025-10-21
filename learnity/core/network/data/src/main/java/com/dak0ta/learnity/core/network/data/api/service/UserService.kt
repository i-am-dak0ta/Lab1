package com.dak0ta.learnity.core.network.data.api.service

import com.dak0ta.learnity.core.network.data.api.dto.student.StudentsResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.tutor.TutorsResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.user.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface UserService {

    @GET("v1/students")
    suspend fun getStudents(): StudentsResponseDto

    @GET("v1/tutors")
    suspend fun getTutors(): TutorsResponseDto

    @GET("v1/users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserResponseDto
}
