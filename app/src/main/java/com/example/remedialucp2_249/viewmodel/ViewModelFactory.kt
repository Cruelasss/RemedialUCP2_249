package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remedialucp2_249.repositori.LibraryRepository

/**
 * Factory untuk menyediakan LibraryRepository ke dalam LibraryViewModel.
 * Diperlukan karena ViewModel standar tidak mendukung parameter constructor secara langsung.
 */
class ViewModelFactory(private val repository: LibraryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Memeriksa apakah modelClass yang diminta adalah LibraryViewModel
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            return LibraryViewModel(repository) as T
        }
        // Melempar exception jika mencoba membuat ViewModel yang tidak dikenal melalui factory ini
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}