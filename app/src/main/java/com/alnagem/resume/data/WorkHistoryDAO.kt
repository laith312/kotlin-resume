package com.alnagem.resume.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkHistoryDAO {

    @Query("SELECT * FROM work_history_table WHERE profile_id = :profileId")
    suspend fun fetchWorkHistory(profileId: Int) : List<WorkHistory>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWorkHistory(workHistoryList: List<WorkHistory>)
}