package com.example.remedialucp2_249.room

import androidx.room.*

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0,
    val name: String,
    val parentId: Long? = null // Mendukung kedalaman tak terbatas
)

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val bookId: Long = 0,
    val title: String,
    val categoryId: Long?,
    val isBorrowed: Boolean = false,
    val isDeleted: Boolean = false // Soft Delete
)

@Entity(tableName = "audit_logs")
data class AuditLogEntity(
    @PrimaryKey(autoGenerate = true) val logId: Long = 0,
    val action: String,
    val note: String,
    val timestamp: Long = System.currentTimeMillis()
)