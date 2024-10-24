package com.example.contract_app.utils

import com.example.contract_app.database.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.UUID

fun importJsonToUsers(jsonString: String): List<User> {
    val gson = Gson()
    val userType = object : TypeToken<List<User>>() {}.type
    val usersFromJson: List<User> = gson.fromJson(jsonString, userType)

    return usersFromJson.map {
        it.copy(id = UUID.randomUUID().toString())
    }
}
