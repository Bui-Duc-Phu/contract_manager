package com.example.contract_app.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(user: User): Long

    @Query("SELECT * FROM User WHERE id = :userID")
    suspend fun getContactById(userID: String): User?

    @Query("SELECT * FROM User")
    suspend fun getAllContacts(): List<User>

    @Update
    suspend fun updateContact(user: User)

    @Delete
    suspend fun deleteContact(user: User)

    @Query("DELETE FROM User")
    suspend fun clearAllContacts()
}

