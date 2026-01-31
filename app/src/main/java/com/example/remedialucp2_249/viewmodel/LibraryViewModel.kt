package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.*
import com.example.remedialucp2_249.repositori.LibraryRepository
import com.example.remedialucp2_249.room.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(private val repository: LibraryRepository) : ViewModel() {

    val categories = repository.allCategories.asLiveData(Dispatchers.IO)

    private val _status = MutableStateFlow("")
    val status = _status.asStateFlow()

    fun addCategory(name: String, pId: String) {
        val parentId = pId.toLongOrNull()
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCategory(CategoryEntity(name = name, parentId = parentId))
            _status.emit("Kategori Berhasil Disimpan")
        }
    }

    fun deleteCategory(category: CategoryEntity, mode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.performSafeDelete(category, mode)
                _status.emit("Berhasil Dihapus")
            } catch (e: Exception) {
                _status.emit(e.message ?: "Error")
            }
        }
    }
}

// Factory untuk inisialisasi ViewModel di MainActivity
class ViewModelFactory(private val repository: LibraryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LibraryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}