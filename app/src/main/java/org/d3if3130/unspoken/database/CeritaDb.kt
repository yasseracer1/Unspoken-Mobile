package org.d3if3130.unspoken.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3130.unspoken.model.Cerita

@Database(entities = [Cerita::class], version = 1, exportSchema = false)
abstract class CeritaDb : RoomDatabase() {
    abstract val dao: CeritaDao

    companion object {

        @Volatile
        private var INSTANCES: CeritaDb? = null

        fun getInstance(context: Context): CeritaDb {
            synchronized(this) {
                var instances = INSTANCES
                if (instances == null) {
                    instances = Room.databaseBuilder(context.applicationContext,
                        CeritaDb::class.java, "ceritaq.db").build()
                    INSTANCES = instances
                }
                return instances
            }
        }
    }

}