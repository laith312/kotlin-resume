package com.alnagem.resume.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_history_table")
data class WorkHistory(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "profile_id") val profileId: Int?,
    @ColumnInfo(name = "company") val companyName: String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "end_date") val endDate: String,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "key_technologies") val keyTechnologies: String,
    @ColumnInfo(name = "description") val description: String
)