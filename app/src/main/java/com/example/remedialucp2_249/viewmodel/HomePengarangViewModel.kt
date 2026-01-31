package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_249.room.Pengarang
import com.example.ucp2.repositori.RepositoriLibrary
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomePengarangViewModel(private val repository: RepositoriLibrary) : ViewModel() {
    val homePengarangUiState: StateFlow<HomePengarangUiState> =
        repository.getAllPengarang().map { HomePengarangUiState(listPengarang = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomePengarangUiState()
            )
}

data class HomePengarangUiState(
    val listPengarang: List<Pengarang.Pengarang> = listOf()
)