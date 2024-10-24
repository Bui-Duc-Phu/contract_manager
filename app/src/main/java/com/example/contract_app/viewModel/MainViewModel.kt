package com.example.contract_app.viewModel

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.contract_app.database.ContactService
import com.example.contract_app.database.User
import com.example.contract_app.paging.UserPagingSource
import com.example.contract_app.utils.importJsonToUsers
import com.example.contract_app.utils.readJsonFromUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactService: ContactService
) : ViewModel() {

    private val _contactList = MutableLiveData<List<User>>()
    val contactList: LiveData<List<User>> = _contactList
    val userFlow: Flow<PagingData<User>> = getUsers()

    fun loadContacts() {
        viewModelScope.launch {
            val contacts = contactService.getContacts()
            _contactList.postValue(contacts)
        }
    }

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(contactService) }
        ).flow.cachedIn(viewModelScope)
    }

    fun importDataToDatabase(context: Context, uri: Uri, onSuccess: (Boolean) -> Unit) {
        try {
            val jsonString = readJsonFromUri(context, uri)
            val userList = importJsonToUsers(jsonString)
            viewModelScope.launch {
                userList.forEach { user ->
                    try {
                        contactService.insertContact(user) // Thêm người dùng vào cơ sở dữ liệu
                    } catch (e: SQLiteConstraintException) {
                        println("Bỏ qua user ${user.name} vì lỗi unique: ${e.message}")
                    }
                }
                loadContacts()
                onSuccess(true)
                Toast.makeText(context, "Dữ liệu đã được nhập thành công!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            onSuccess(false)
            println("Lỗi khi đọc file: " + e.message)
            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearAll(onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                contactService.clearAllContacts()
                onSuccess(true)
            } catch (e: Exception) {
                onSuccess(false)
                println("Lỗi khi xóa dữ liệu: ${e.message}")
            }
        }
    }









}
