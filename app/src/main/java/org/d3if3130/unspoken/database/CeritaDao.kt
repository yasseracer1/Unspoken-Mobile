package org.d3if3130.unspoken.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3130.unspoken.model.Cerita

@Dao
interface CeritaDao {

    @Insert
    suspend fun insert(cerita: Cerita)

    @Update
    suspend fun update(cerita: Cerita)

    @Query("SELECT * FROM cerita ORDER BY tanggal ASC")
    fun getCerita(): Flow<List<Cerita>>

    @Query("SELECT * FROM cerita WHERE id = :id")
    suspend fun getCeritaById(id: Long): Cerita?

    @Query("DELETE FROM cerita WHERE id = :id")
    suspend fun deleteById(id: Long)
}