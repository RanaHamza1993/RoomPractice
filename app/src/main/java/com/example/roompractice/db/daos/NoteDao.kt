package com.example.roompractice.db.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.roompractice.db.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun insert(note:NoteEntity)
}