package com.dak0ta.learnity.core.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messengers")
data class MessengerEntity(
    @PrimaryKey val id: String,
)
