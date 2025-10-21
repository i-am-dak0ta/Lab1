package com.dak0ta.learnity.core.database.domain.repository

import com.dak0ta.learnity.core.domain.course.Course
import kotlinx.coroutines.flow.Flow

interface CourseLocalRepository {

    fun observeCourse(id: String): Flow<Course?>
    fun observeCoursesByTutor(tutorId: String): Flow<List<Course>>
    fun observeAllCourses(): Flow<List<Course>>
    suspend fun getCourseById(id: String): Course?
    suspend fun getCoursesByTutor(tutorId: String): List<Course>
    suspend fun getAllCourses(): List<Course>
    suspend fun upsertCourse(course: Course)
    suspend fun upsertCourses(courses: List<Course>)
    suspend fun deleteCourse(id: String)
    suspend fun deleteAllCourses()
}
