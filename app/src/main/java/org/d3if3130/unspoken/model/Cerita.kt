package org.d3if3130.unspoken.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cerita")
data class Cerita(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val catatan: String,
    val tema: String,
    val tanggal: String
)