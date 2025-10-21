package com.dak0ta.learnity.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dak0ta.learnity.core.database.data.entity.MessengerEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessengerDao {

    @Query("SELECT * FROM messengers WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: String): Flow<MessengerEntity?>

    @Query("SELECT * FROM messengers")
    fun getAllFlow(): Flow<List<MessengerEntity>>

    @Query("SELECT * FROM messengers WHERE id = :id LIMIT 1")
    suspend fun getByIdOnce(id: String): MessengerEntity?

    @Query("SELECT * FROM messengers")
    suspend fun getAllOnce(): List<MessengerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(m: MessengerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messengers: List<MessengerEntity>)

    @Query("DELETE FROM messengers WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM messengers")
    suspend fun deleteAll()
}
