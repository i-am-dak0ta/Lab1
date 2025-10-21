package com.dak0ta.learnity.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dak0ta.learnity.core.database.data.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CourseDao {

    @Query("SELECT * FROM courses WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: String): Flow<CourseEntity?>

    @Query("SELECT * FROM courses WHERE tutor_id = :tutorId")
    fun getByTutorFlow(tutorId: String): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses")
    fun getAllFlow(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE id = :id LIMIT 1")
    suspend fun getByIdOnce(id: String): CourseEntity?

    @Query("SELECT * FROM courses WHERE tutor_id = :tutorId")
    suspend fun getByTutorOnce(tutorId: String): List<CourseEntity>

    @Query("SELECT * FROM courses")
    suspend fun getAllOnce(): List<CourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<CourseEntity>)

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM courses")
    suspend fun deleteAll()
}
