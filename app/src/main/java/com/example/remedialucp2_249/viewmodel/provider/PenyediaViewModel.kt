package com.example.remedialucp2_249.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remedialucp2_249.repository.PerpustakaanApp
import com.example.remedialucp2_249.viewmodel.BukuViewModel
import com.example.remedialucp2_249.viewmodel.DetailBukuViewModel
import com.example.remedialucp2_249.viewmodel.DetailPengarangViewModel
import com.example.remedialucp2_249.viewmodel.EditBukuViewModel
import com.example.remedialucp2_249.viewmodel.EditPengarangViewModel
import com.example.remedialucp2_249.viewmodel.HomeBukuViewModel
import com.example.remedialucp2_249.viewmodel.HomePengarangViewModel
import com.example.remedialucp2_249.viewmodel.PengarangViewModel



object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            PengarangViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            BukuViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            HomePengarangViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            HomeBukuViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            DetailBukuViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            EditBukuViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            DetailPengarangViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            EditPengarangViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)