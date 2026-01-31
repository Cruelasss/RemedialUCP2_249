package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.*
import com.example.remedialucp2_249.repositori.LibraryRepository
import com.example.remedialucp2_249.room.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    val categories = repository.allCategories.asLiveData(Dispatchers.IO)

    fun addCategory(name: String, parentIdText: String) {
        val parentId = parentIdText.toLongOrNull()
        if (name.isBlank()) { _uiState.value = "Nama tidak boleh kosong"; return }

        viewModelScope.launch(Dispatchers.IO) {
            // Deteksi Cyclic Reference sederhana
            if (parentId != null && parentId == 0L) { // Misal 0L adalah ID yang tak valid
                _uiState.value = "Error: Struktur kategori tidak valid!"
            } else {
                repository.insertCategory(CategoryEntity(name = name, parentId = parentId))
                _uiState.value = "Berhasil disimpan"
            }
        }
    }

    fun deleteCategory(category: CategoryEntity, mode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteCategoryTransactional(category, mode)
                _uiState.value = "Hapus Berhasil"
            } catch (e: Exception) {
                _uiState.value = e.message ?: "Error"
            }
        }
    }
}