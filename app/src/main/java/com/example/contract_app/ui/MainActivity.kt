package com.example.contract_app.ui
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contract_app.adapter.MainAdapter
import com.example.contract_app.databinding.ActivityMainBinding
import com.example.contract_app.utils.importJsonToUsers
import com.example.contract_app.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel: MainViewModel by viewModels()
    private val REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        _init()
    }

    private fun _init() {
        handleRecylerview()

        binding.buttonAddContact.setOnClickListener {
            startActivity(Intent(this,AddOneContact::class.java))
        }

        binding.buttonImportContacts.setOnClickListener {
            handlerInportJosn()

        }

        binding.clearBtn.setOnClickListener {
            mainViewModel.clearAll(){ onSuceess ->
                if(onSuceess)  handleRecylerview()
            }

        }

    }

    private fun handlerInportJosn() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/json"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun handleRecylerview() {
        binding.recylerview.layoutManager = LinearLayoutManager(this)
        val adapter = MainAdapter()
        binding.recylerview.adapter = adapter
        lifecycleScope.launch {
            mainViewModel.getUsers().collectLatest { pagingData ->
                println( "paging data : "+ pagingData . toString())
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                // Đọc file JSON từ URI
                mainViewModel.importDataToDatabase(this,uri){ onSuccess ->
                    if(!onSuccess ) return@importDataToDatabase
                    handleRecylerview()
                }
            }
        }
    }



}
