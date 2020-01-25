package com.example.roompractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.RoomDatabase
import com.example.roompractice.db.NoteRoomDataBase
import com.example.roompractice.db.daos.NoteDao

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = this.javaClass.simpleName
    private var noteDao:NoteDao?=null
    private var noteDB:NoteRoomDataBase?=null
    init {

        noteDB = NoteRoomDataBase.getAppDataBase(application)
        noteDao=noteDB?.noteDao()
    }

    override fun onCleared() {
        super.onCleared()

    }
}
