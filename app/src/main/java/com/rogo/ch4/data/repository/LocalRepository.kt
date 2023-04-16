package com.rogo.ch4.data.repository

import android.app.Application
import com.rogo.ch4.data.local.database.AppDatabase
import com.rogo.ch4.data.local.database.dao.NoteDao
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.local.preferences.DataStoreManager
import kotlinx.coroutines.flow.Flow


class LocalRepository(
    private val dataStoreManager: DataStoreManager,
    application: Application
) {
    private val mNotesDao: NoteDao

    init {
        val db = AppDatabase.getInstance(application)
        mNotesDao = db.noteDao()
    }

    suspend fun insertNote(noteEntity: NoteEntity) = mNotesDao.insertNote(noteEntity)

    suspend fun updateNote(noteEntity: NoteEntity) = mNotesDao.updateNote(noteEntity)

    suspend fun getNotesAsc(): List<NoteEntity> = mNotesDao.getNotesAsc()

    suspend fun getNotesDesc(): List<NoteEntity> = mNotesDao.getNotesDesc()

    suspend fun getNote(noteId: Int): NoteEntity = mNotesDao.getNote(noteId)

    suspend fun deleteNote(noteEntity: NoteEntity) = mNotesDao.deleteNote(noteEntity)

    fun getFilter(): Flow<String> = dataStoreManager.getFilter

    suspend fun setFilter(filter: String) = dataStoreManager.setFilter(filter)

}