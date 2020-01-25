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
    companion object {
        var INSTANCE: NoteRoomDataBase? = null

        fun getAppDataBase(context: Context): NoteRoomDataBase? {
            if (INSTANCE == null){
                synchronized(RoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteRoomDataBase::class.java, "notes_db").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}