package com.example.remedialucp2_249.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM books WHERE categoryId IN (:categoryIds) AND isDeleted = 0")
    fun getBooksInCategories(categoryIds: List<Long>): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert
    suspend fun insertLog(log: AuditLogEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("DELETE FROM categories WHERE categoryId = :id")
    suspend fun deleteCategory(id: Long)

    @Query("SELECT COUNT(*) FROM books WHERE categoryId = :catId AND isBorrowed = 1")
    suspend fun countBorrowedInPath(catId: Long): Int

    @Query("UPDATE books SET isDeleted = 1 WHERE categoryId = :catId")
    suspend fun softDeleteBooksByCat(catId: Long)

    @Query("UPDATE books SET categoryId = NULL WHERE categoryId = :catId")
    suspend fun detachBooksFromCat(catId: Long)
}