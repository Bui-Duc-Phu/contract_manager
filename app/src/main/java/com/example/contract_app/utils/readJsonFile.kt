package com.example.contract_app.utils

import android.content.Context
import android.net.Uri
import java.io.BufferedReader
import java.io.InputStreamReader


fun readJsonFromUri(context: Context, uri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    return bufferedReader.use { it.readText() }
}
