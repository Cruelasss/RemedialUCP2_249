package com.example.remedialucp2_249

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.remedialucp2_249.room.AppDatabase
import com.example.remedialucp2_249.repositori.LibraryRepository
import com.example.remedialucp2_249.repository.LibraryRepository
import com.example.remedialucp2_249.viewmodel.LibraryViewModel
import com.example.remedialucp2_249.view.LibraryScreen
import com.example.remedialucp2_249.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "remedial_db_249"
        ).build()

        // 2. Inisialisasi Repository & ViewModel
        val repository = LibraryRepository(db)
        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[LibraryViewModel::class.java]

        // 3. Render UI
        setContent {
            LibraryScreen(viewModel)
        }
    }
}