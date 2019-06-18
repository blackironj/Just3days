package com.just3days.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class DeterminationInfo(
    var start_time: Int,
    var last_check_time: Int,
    var content: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}