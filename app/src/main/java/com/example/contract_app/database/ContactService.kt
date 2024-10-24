package com.example.contract_app.database

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactService @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend fun insertContact(contact: User) {
        contactRepository.insertContact(contact)
    }

    suspend fun getContacts(): List<User> {
        return contactRepository.getAllContacts()
    }

    suspend fun clearAllContacts(){
        return contactRepository.clearAllContacts()
    }
}
