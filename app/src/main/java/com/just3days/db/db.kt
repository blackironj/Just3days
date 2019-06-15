package com.just3days.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [DeterminationInfo::class], version = 1)
abstract class DeterminationDB : RoomDatabase() {
    abstract fun determinationInfoDao(): DeterminationInfoDao

    companion object {
        private var INSTANCE: DeterminationDB? = null

        fun getInstance(context: Context): DeterminationDB? {
            if (INSTANCE == null) {
                synchronized(DeterminationDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DeterminationDB::class.java, "determinaion.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}