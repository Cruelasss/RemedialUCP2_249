package com.example.remedialucp2_249.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE categoryId = :id")
    suspend fun getCategoryById(id: Long): CategoryEntity?

    @Query("SELECT COUNT(*) FROM books WHERE categoryId = :catId AND isBorrowed = 1")
    suspend fun countBorrowedBooks(catId: Long): Int

    @Query("UPDATE books SET isDeleted = 1 WHERE categoryId = :catId")
    suspend fun softDeleteBooksInCategory(catId: Long)

    @Query("UPDATE books SET categoryId = NULL WHERE categoryId = :catId")
    suspend fun detachBooksFromCategory(catId: Long)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Insert
    suspend fun insertLog(log: AuditLogEntity)
}