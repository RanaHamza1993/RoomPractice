package com.example.roompractice.viewmodels

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roompractice.db.NoteRoomDataBase
import com.example.roompractice.db.daos.NoteDao
import com.example.roompractice.db.entities.NoteEntity

class EditNoteViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private val TAG = this.javaClass.simpleName
    private val noteDao: NoteDao?
    private val db: NoteRoomDataBase?

    init {
        Log.i(TAG, "Edit ViewModel")
        db = NoteRoomDataBase.getAppDataBase(application)
        noteDao = db?.noteDao()
    }

    fun getNote(noteId: String): LiveData<NoteEntity> {
        return noteDao!!.getNote(noteId)
    }
}
