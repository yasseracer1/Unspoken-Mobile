package org.d3if3130.unspoken.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import org.d3if3130.unspoken.model.User
//
//@Dao
//interface UserDao {
//
//    @Insert
//    suspend fun insert(user: User)
//
//    @Query("SELECT * FROM user WHERE id = :id")
//    suspend fun getUserById(id: Long): User?
//}