package com.dak0ta.learnity.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dak0ta.learnity.core.database.data.entity.TutorEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TutorDao {

    @Query("SELECT * FROM tutors WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: String): Flow<TutorEntity?>

    @Query("SELECT * FROM tutors")
    fun getAllFlow(): Flow<List<TutorEntity>>

    @Query("SELECT * FROM tutors WHERE id = :id LIMIT 1")
    suspend fun getByIdOnce(id: String): TutorEntity?

    @Query("SELECT * FROM tutors")
    suspend fun getAllOnce(): List<TutorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tutor: TutorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tutors: List<TutorEntity>)

    @Query("DELETE FROM tutors WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM tutors")
    suspend fun deleteAll()
}
