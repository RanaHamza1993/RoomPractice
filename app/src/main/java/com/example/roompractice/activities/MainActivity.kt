package com.example.roompractice.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.roompractice.R
import com.example.roompractice.db.entities.NoteEntity
import com.example.roompractice.extensions.showInfoMessage
import com.example.roompractice.extensions.showSuccessMessage
import com.example.roompractice.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.ViewModelProviders as ViewModelProviders1

class MainActivity : AppCompatActivity(), CoroutineScope {
    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private var noteViewModel: NoteViewModel?=null
    private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job= Job()
        setContentView(R.layout.activity_main)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        })

        noteViewModel = ViewModelProviders1.of(this).get(NoteViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //code to insert the note

            val note_id = UUID.randomUUID().toString()
            val note = NoteEntity(note_id, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel?.insert(note)






        }else{
            showInfoMessage("Note not inserted")
        }
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}
