//package org.d3if3130.unspoken.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import org.d3if3130.unspoken.model.User
//
//@Database(entities = [User::class], version = 1, exportSchema = false)
//abstract class UserDb : RoomDatabase() {
//    abstract val dao: UserDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCES: UserDb? = null
//
//        fun getInstance(context: Context): UserDb {
//            synchronized(this) {
//                var instances = INSTANCES
//                if (instances == null) {
//                    instances = Room.databaseBuilder(context.applicationContext,
//                        UserDb::class.java, "user.db").build()
//                    INSTANCES = instances
//                }
//                return instances
//            }
//        }
//    }
//
//}