package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.*
import com.example.remedialucp2_249.repository.LibraryRepository
import com.example.remedialucp2_249.room.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class LibraryViewModel(private val repository: LibraryRepository) : ViewModel() {

    val categories = repository.allCategories // Dari repository

    // Validasi Input (Mencegah Kesalahan Entri Data)
    fun addCategoryWithValidation(name: String, parentIdText: String) {
        val parentId = parentIdText.toLongOrNull() // Validasi tipe data Long

        if (name.isBlank()) {
            _uiState.value = "Error: Nama tidak boleh kosong"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            // Deteksi Cyclic Reference (Sederhana)
            if (parentId != null && isParentSameAsChild(parentId)) {
                _uiState.value = "Error: Cyclic Reference terdeteksi!"
            } else {
                repository.insertCategory(CategoryEntity(name = name, parentId = parentId))
                _uiState.value = "Kategori Berhasil Ditambahkan"
            }
        }
    }

    private fun isParentSameAsChild(parentId: Long): Boolean {
        // Implementasi logika pengecekan hirarki di sini
        return false
    }
}