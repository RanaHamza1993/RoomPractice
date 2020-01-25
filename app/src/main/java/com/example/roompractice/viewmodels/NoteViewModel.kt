package com.example.roompractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roompractice.db.NoteRoomDataBase
import com.example.roompractice.db.daos.NoteDao
import com.example.roompractice.db.entities.NoteEntity
import com.example.roompractice.extensions.showSuccessMessage
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NoteViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val TAG = this.javaClass.simpleName
    private var noteDao:NoteDao?=null
    lateinit var context:Application
    private var noteDB:NoteRoomDataBase?=null
    init {
        job=Job()
        context=application
        noteDB = NoteRoomDataBase.getAppDataBase(application)
        noteDao=noteDB?.noteDao()
    }

    fun insert(note:NoteEntity){
        launch(coroutineContext) {

            withContext(Dispatchers.Default) {
             noteDao?.insert(note)
            }
            context.showSuccessMessage("Note added successfully")

        }
    }
    override fun onCleared() {
        job.cancel()
        super.onCleared()

    }

}
