package com.rogo.ch4.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rogo.ch4.data.local.database.dao.NoteDao
import com.rogo.ch4.data.local.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DB_NAME = "note.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(ctx: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class.java) {

                val instances = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java, DB_NAME
                ).build()
                instances
            }
        }
    }
}