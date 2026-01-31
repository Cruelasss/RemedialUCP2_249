package com.example.remedialucp2_249.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Database(entities = [BookEntity::class, CategoryEntity::class, AuditLogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao
}