package com.example.remedialucp2_249.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.remedialucp2_249.room.CategoryEntity
import com.example.remedialucp2_249.viewmodel.LibraryViewModel

@Composable
fun LibraryScreen(viewModel: LibraryViewModel) {
    val categories by viewModel.categories.observeAsState(emptyList())
    val uiState by viewModel.uiState.collectAsState()
    var selectedCat by remember { mutableStateOf<CategoryEntity?>(null) }

    Column(Modifier.padding(16.dp)) {
        Text("Status: $uiState", color = MaterialTheme.colorScheme.primary)

        // List Kategori
        LazyColumn {
            items(categories) { cat ->
                Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(if (cat.parentId != null) "-- ${cat.name}" else cat.name)
                    Button(onClick = { selectedCat = cat }) { Text("Hapus") }
                }
            }
        }
    }

    if (selectedCat != null) {
        AlertDialog(
            onDismissRequest = { selectedCat = null },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Pilih aksi untuk buku di kategori ${selectedCat?.name}") },
            confirmButton = {
                Button(onClick = { viewModel.deleteCategory(selectedCat!!, "SOFT_DELETE"); selectedCat = null }) { Text("Soft Delete") }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.deleteCategory(selectedCat!!, "DETACH"); selectedCat = null }) { Text("Pindah ke Tanpa Kategori") }
            }
        )
    }
}