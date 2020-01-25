package com.example.roompractice.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roompractice.db.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun insert(note:NoteEntity):Unit
    @Query("SELECT * FROM notes")
    fun getAllNotes():LiveData<List<NoteEntity>>
    @Query("SELECT * FROM notes WHERE id=:noteId")
    abstract fun getNote(noteId: String): LiveData<NoteEntity>
    @Update
    abstract fun update(note: NoteEntity)

    @Delete
    fun deleteData(note:NoteEntity)
}