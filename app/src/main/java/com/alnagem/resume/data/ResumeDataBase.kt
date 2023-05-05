package com.alnagem.resume.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class, WorkHistory::class], version = 1, exportSchema = false)
abstract class ResumeDataBase : RoomDatabase() {

    abstract fun profileDAO(): ProfileDAO

    companion object {
        private var INSTANCE: ResumeDataBase? = null

        fun getDatabase(context: Context): ResumeDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResumeDataBase::class.java,
                    "resume_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}