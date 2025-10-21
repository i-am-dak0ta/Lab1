package com.dak0ta.learnity.core.network.data.repository

import android.util.Log
import com.dak0ta.learnity.core.domain.course.Course
import com.dak0ta.learnity.core.network.data.api.service.CourseService
import com.dak0ta.learnity.core.network.data.mapper.toDomain
import com.dak0ta.learnity.core.network.data.network.SafeApiCall
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.CourseRemoteRepository

internal class CourseRemoteRepositoryImpl(
    private val service: CourseService,
    private val safeApiCall: SafeApiCall,
) : CourseRemoteRepository {

    override suspend fun getCourses(): ApiResult<List<Course>> {
        Log.d(TAG, "Getting all courses")
        return safeApiCall("GET_COURSES") { service.getCourses().map { it.toDomain() } }
    }

    override suspend fun getCourseById(id: String): ApiResult<Course> {
        Log.d(TAG, "Getting course by ID: $id")
        return safeApiCall("GET_COURSE_BY_ID") { service.getCourseById(id).toDomain() }
    }

    private companion object {

        const val TAG = "Learnity:CourseRemoteRepositoryImpl"
    }
}
