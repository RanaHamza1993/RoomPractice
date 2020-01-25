package com.example.roompractice.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(@PrimaryKey val id:String,@ColumnInfo(name = "note")val mNote:String)