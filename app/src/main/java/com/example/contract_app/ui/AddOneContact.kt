package com.example.contract_app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.contract_app.database.User
import com.example.contract_app.databinding.ActivityAddOneContactBinding
import com.example.contract_app.viewModel.AddOneContactViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddOneContact : AppCompatActivity() {
    private val binding: ActivityAddOneContactBinding by lazy {
        ActivityAddOneContactBinding.inflate(layoutInflater)
    }

    private val addOneContactViewModel : AddOneContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        _init()
    }

    private fun _init() {
        binding.saveBtn.setOnClickListener {
            handlerAddUser()
        }
    }

    private fun handlerAddUser() {
        val name = binding.nameEdt.text.toString().trim()
        val phone = binding.phoneEdt.text.toString().trim()
        val email = binding.emailEdt.text.toString().trim()
        if (name.isEmpty()) {
            binding.nameEdt.error = "Name is required"
            return
        }
        if (phone.isEmpty()) {
            binding.phoneEdt.error = "Phone number is required"
            return
        }
        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEdt.error = "Invalid email format"
            return
        }
        addOneContactViewModel.addUser(User(
            name = name,
            email = email,
            phone =phone
        ),this){ onSuccess->
            if(!onSuccess) return@addUser
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}