package com.example.roompractice.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roompractice.db.entities.Note

@Database(entities =Note::class.java ,version = 1)
abstract class NoteRoomDataBase :RoomDatabase() {
}