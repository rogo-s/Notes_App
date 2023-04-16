package com.rogo.ch4.data.local.database.dao

import androidx.room.*
import com.rogo.ch4.data.local.database.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table ORDER BY title ASC")
    suspend fun getNotesAsc(): List<NoteEntity>

    @Query("SELECT * FROM note_table ORDER BY title DESC")
    suspend fun getNotesDesc(): List<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id = :noteId")
    suspend fun getNote(noteId: Int): NoteEntity

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}