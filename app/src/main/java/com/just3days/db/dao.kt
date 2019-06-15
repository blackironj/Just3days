package com.just3days.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface DeterminationInfoDao {
    @Query("SELECT * FROM DeterminationInfo")
    fun getData(): List<DeterminationInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info: DeterminationInfo)

    @Query("DELETE from DeterminationInfo")
    fun deleteData()

    @Query("UPDATE DeterminationInfo SET last_check_time = :last_check_time WHERE id = :id")
    fun update(id: Long, last_check_time: String)

    @Query("SELECT count(*) FROM DeterminationInfo")
    fun isEmpty(): Int
}
