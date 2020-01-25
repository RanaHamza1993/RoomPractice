package com.example.roompractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roompractice.db.daos.NoteDao
import com.example.roompractice.db.entities.NoteEntity

@Database(entities = [NoteEntity::class],version = 1)
abstract class NoteRoomDataBase :RoomDatabase() {

    abstract fun noteDao():NoteDao
    @Volatile
    private var noteRoomInstance: NoteRoomDataBase? = null
    internal fun getDatabase(context: Context): NoteRoomDataBase {
        if (noteRoomInstance == null) {
            synchronized(NoteRoomDataBase::class.java) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDataBase::class.java, "note_database"
                    )
                        .build()
                }
            }
        }
        return noteRoomInstance!!
    }

}