package com.just3days.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class DeterminationInfo(@PrimaryKey(autoGenerate = true) val id: Long,
                             var start_time: Long,
                             var last_check_time: Long,
                             var content: String)