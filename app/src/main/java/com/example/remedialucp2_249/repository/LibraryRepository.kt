package com.example.remedialucp2_249.repositori

import androidx.room.withTransaction
import com.example.remedialucp2_249.room.*

class LibraryRepository(private val db: AppDatabase) {
    private val dao = db.libraryDao()
    val allCategories = dao.getAllCategories()

    suspend fun insertCategory(category: CategoryEntity) = dao.insertCategory(category)

    suspend fun deleteCategoryTransactional(category: CategoryEntity, deleteMode: String) {
        db.withTransaction {
            // 1. Cek status pinjam (Syarat Rollback)
            val borrowed = dao.countBorrowedBooks(category.categoryId)
            if (borrowed > 0) throw Exception("ROLLBACK: $borrowed buku masih dipinjam!")

            // 2. Opsi Penghapusan Dinamis
            if (deleteMode == "SOFT_DELETE") {
                dao.softDeleteBooksInCategory(category.categoryId)
            } else {
                dao.detachBooksFromCategory(category.categoryId)
            }

            // 3. Hapus Kategori & Audit Log
            dao.deleteCategory(category)
            dao.insertLog(AuditLogEntity(action = "DELETE", note = "Kategori ${category.name} dihapus mode $deleteMode"))
        }
    }
}