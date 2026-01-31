package com.example.remedialucp2_249.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_249.room.Buku
import com.example.remedialucp2_249.view.route.DestinasiDetailBuku
import com.example.ucp2.repositori.RepositoriLibrary
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoriLibrary
) : ViewModel() {
    private val bukuId: Int = checkNotNull(savedStateHandle[DestinasiDetailBuku.bukuIdArg])

    val detailBukuUiState: StateFlow<DetailBukuUiState> =
        repository.getBuku(bukuId)
            .filterNotNull()
            .map { DetailBukuUiState(bukuEvent = it.toBukuEvent()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = DetailBukuUiState()
            )

    suspend fun deleteBuku() {
        val currentBuku = detailBukuUiState.value.bukuEvent.toBuku()
        repository.deleteBuku(currentBuku)
    }
}

data class DetailBukuUiState(
    val bukuEvent: BukuEvent = BukuEvent()
)

fun Buku.Buku.toBukuEvent(): BukuEvent = BukuEvent(
    id = id,
    judul = judul,
    isbn = isbn,
    nama_pengarang = nama_pengarang,
    nama_kategori = nama_kategori,
    status_pinjam = status_pinjam
)