package com.example.contract_app.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.contract_app.databinding.ActivityTestBinding

import com.example.contract_app.database.ContactRepository
import com.example.contract_app.database.ContactService
import com.example.contract_app.database.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    @Inject
    lateinit var contactRepository: ContactRepository

    private val binding : ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var contactService: ContactService



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        contactService = ContactService(contactRepository)


    }

    var userList = listOf(
        User(
            name = "Nguyễn Văn A",
            email = "nguyenvana@example.com",
            phone = "0123456789"
        ),
        User(
            name = "Trần Thị B",
            email = "tranthib@example.com",
            phone = "0987654321"
        ),
        User(
            name = "Lê Văn C",
            email = "levanc@example.com",
            phone = "0112233445"
        ),
        User(
            name = "Phạm Thị D",
            email = "phamthid@example.com",
            phone = "0667788990"
        ),
        User(
            name = "Hoàng Văn E",
            email = "hoangvane@example.com",
            phone = "0555666778"
        ),
        User(
            name = "Đặng Thị F",
            email = "dangthif@example.com",
            phone = "0444455566"
        ),
        User(
            name = "Vũ Văn G",
            email = "vuvang@example.com",
            phone = "0333344445"
        ),
        User(
            name = "Nguyễn Thị H",
            email = "nguyenthih@example.com",
            phone = "0222233334"
        ),
        User(
            name = "Trần Văn I",
            email = "tranvani@example.com",
            phone = "0111122223"
        ),
        User(
            name = "Phan Thị J",
            email = "panthij@example.com",
            phone = "0101010101"
        )
    )

    private fun saveContact(name: String, email: String, phone: String) {
        val newContact = User(name = name, email = email, phone = phone)
        lifecycleScope.launch {
            contactService.insertContact(newContact)


        }
    }
}

