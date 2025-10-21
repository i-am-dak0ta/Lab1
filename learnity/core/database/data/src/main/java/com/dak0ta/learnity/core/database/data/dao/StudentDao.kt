package com.dak0ta.learnity.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dak0ta.learnity.core.database.data.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface StudentDao {

    @Query("SELECT * FROM students WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: String): Flow<StudentEntity?>

    @Query("SELECT * FROM students")
    fun getAllFlow(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE id = :id LIMIT 1")
    suspend fun getByIdOnce(id: String): StudentEntity?

    @Query("SELECT * FROM students")
    suspend fun getAllOnce(): List<StudentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: List<StudentEntity>)

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM students")
    suspend fun deleteAll()
}
