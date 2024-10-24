package com.example.contract_app.database



class ContactRepository(
    private val contactDao: ContactDao
) {
    suspend fun insertContact(contact: User) {
        contactDao.insertContact(contact)
    }
    suspend fun getContactById(contactId: String): User? {
        return contactDao.getContactById(contactId)
    }
    suspend fun getAllContacts(): List<User> {
        return contactDao.getAllContacts()
    }
    suspend fun updateContact(contact: User) {
        contactDao.updateContact(contact)
    }
    suspend fun deleteContact(contact: User) {
        contactDao.deleteContact(contact)
    }
    suspend fun clearAllContacts(){
        contactDao.clearAllContacts()
    }
}
