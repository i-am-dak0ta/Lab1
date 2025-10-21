package com.dak0ta.learnity.core.network.domain.repository

import com.dak0ta.learnity.core.domain.course.Course
import com.dak0ta.learnity.core.network.domain.model.ApiResult

interface CourseRemoteRepository {

    suspend fun getCourses(): ApiResult<List<Course>>
    suspend fun getCourseById(id: String): ApiResult<Course>
}
