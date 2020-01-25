package com.example.roompractice.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.adapters.NoteListAdapter
import com.example.roompractice.db.entities.NoteEntity
import com.example.roompractice.extensions.showInfoMessage
import com.example.roompractice.extensions.showSuccessMessage
import com.example.roompractice.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.ViewModelProviders as ViewModelProviders1

class MainActivity : AppCompatActivity(), CoroutineScope,NoteListAdapter.OnDeleteClickListener {
    override fun OnDeleteClickListener(myNote: NoteEntity) {
        noteViewModel?.deleteNote(myNote)
    }


    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private var noteViewModel: NoteViewModel?=null
    private var noteListAdapter: NoteListAdapter? = null

    companion object {
        val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
      val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job= Job()
        setContentView(R.layout.activity_main)
        noteViewModel = ViewModelProviders1.of(this).get(NoteViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        noteListAdapter = NoteListAdapter(this, this)
        recyclerView.setAdapter(noteListAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        })

        noteViewModel?.getAllNotes()?.observe(this,androidx.lifecycle.Observer {
            noteListAdapter?.setNotes(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //code to insert the note

            val note_id = UUID.randomUUID().toString()
            val note = NoteEntity(note_id, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
          //  noteViewModel?.insert(note)






        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            // Code to update the note
            val note = NoteEntity(
                data!!.getStringExtra(EditNoteActivity.NOTE_ID),
                data.getStringExtra(EditNoteActivity.UPDATED_NOTE)
            )
            noteViewModel?.update(note)
        }else{
            showInfoMessage("Note not inserted")
        }
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}
