package com.dak0ta.learnity.core.network.data.api.service

import com.dak0ta.learnity.core.network.data.api.dto.course.CourseDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface CourseService {

    @POST("courses")
    suspend fun getCourses(): List<CourseDto>

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: String): CourseDto
}
