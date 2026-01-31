package com.example.remedialucp2_249.repositori

import androidx.room.withTransaction
import com.example.remedialucp2_249.room.*

class LibraryRepository(private val db: AppDatabase) {
    private val dao = db.libraryDao()

    val allCategories = dao.getAllCategories()

    suspend fun insertCategory(category: CategoryEntity) = dao.insertCategory(category)

    suspend fun performSafeDelete(category: CategoryEntity, mode: String) {
        db.withTransaction {
            val borrowed = dao.countBorrowedBooks(category.categoryId)
            if (borrowed > 0) throw Exception("GAGAL: $borrowed buku masih dipinjam!")

            if (mode == "SOFT_DELETE") dao.softDeleteBooks(category.categoryId)
            else dao.detachBooks(category.categoryId)

            dao.deleteCategory(category)
            dao.insertLog(AuditLogEntity(action = "DELETE", note = "Hapus kategori ${category.name}"))
        }
    }
}