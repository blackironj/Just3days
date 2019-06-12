package com.just3days.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
@Dao
interface DeterminationInfoDao  {
    @Query("SELECT * FROM DeterminationInfo")
    fun getData() : DeterminationInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info : DeterminationInfo)

    @Query("DELETE from DeterminationInfo")
    fun deleteData()

    @Query("SELECT COUNT(*) FROM DeterminationInfo")
    fun isEmpty() : Int

}