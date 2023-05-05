package com.alnagem.resume.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_history_table")
data class WorkHistory(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "company") val companyName:String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "end_date") val endDate: String,

    )