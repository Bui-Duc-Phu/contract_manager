package com.example.contract_app.viewModel

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contract_app.database.ContactService
import com.example.contract_app.database.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddOneContactViewModel @Inject constructor(
    private val contactService: ContactService
) : ViewModel() {

    fun addUser(user: User, context: Context, onSuccess  : (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                contactService.insertContact(user)
                onSuccess(true)
                Toast.makeText(context, "Add thành công", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                onSuccess(false)
                Toast.makeText(context, "data exits!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                onSuccess(false)
                Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show()
            }
        }
    }



}