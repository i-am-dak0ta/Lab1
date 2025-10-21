package com.dak0ta.learnity.core.database.data.repository

import com.dak0ta.learnity.core.database.data.dao.CourseDao
import com.dak0ta.learnity.core.database.data.mapper.toDomain
import com.dak0ta.learnity.core.database.data.mapper.toEntity
import com.dak0ta.learnity.core.database.domain.repository.CourseLocalRepository
import com.dak0ta.learnity.core.domain.course.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
internal class CourseLocalRepositoryImpl(
    private val dao: CourseDao,
) : CourseLocalRepository {

    override fun observeCourse(id: String): Flow<Course?> {
        return dao.getByIdFlow(id).map { it?.toDomain() }
    }

    override fun observeCoursesByTutor(tutorId: String): Flow<List<Course>> {
        return dao.getByTutorFlow(tutorId).map { it.map { e -> e.toDomain() } }
    }

    override fun observeAllCourses(): Flow<List<Course>> {
        return dao.getAllFlow().map { it.map { e -> e.toDomain() } }
    }

    override suspend fun getCourseById(id: String): Course? {
        return dao.getByIdOnce(id)?.toDomain()
    }

    override suspend fun getCoursesByTutor(tutorId: String): List<Course> {
        return dao.getByTutorOnce(tutorId).map { it.toDomain() }
    }

    override suspend fun getAllCourses(): List<Course> {
        return dao.getAllOnce().map { it.toDomain() }
    }

    override suspend fun upsertCourse(course: Course) {
        dao.insert(course.toEntity())
    }

    override suspend fun upsertCourses(courses: List<Course>) {
        dao.insertAll(courses.map { it.toEntity() })
    }

    override suspend fun deleteCourse(id: String) {
        dao.deleteById(id)
    }

    override suspend fun deleteAllCourses() {
        dao.deleteAll()
    }
}
