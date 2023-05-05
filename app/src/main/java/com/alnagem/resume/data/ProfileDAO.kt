package com.alnagem.resume.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profile: Profile)

    @Query("SELECT * FROM profile_table ORDER BY id ASC")
    fun getAllProfiles(): List<Profile>
}