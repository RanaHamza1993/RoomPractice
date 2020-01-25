package com.example.roompractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.RoomDatabase
import com.example.roompractice.db.NoteRoomDataBase
import com.example.roompractice.db.daos.NoteDao
import com.example.roompractice.db.entities.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val TAG = this.javaClass.simpleName
    private var noteDao:NoteDao?=null
    private var noteDB:NoteRoomDataBase?=null
    init {
        job=Job()
        noteDB = NoteRoomDataBase.getAppDataBase(application)
        noteDao=noteDB?.noteDao()
    }

    fun insert(note:NoteEntity){
        launch(coroutineContext){
            noteDao?.insert(note)
        }
    }
    override fun onCleared() {
        job.cancel()
        super.onCleared()

    }
}
