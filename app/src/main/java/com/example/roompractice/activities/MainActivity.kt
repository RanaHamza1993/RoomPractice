package com.example.roompractice.activities

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.roompractice.R
import com.example.roompractice.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var noteViewModel: NoteViewModel?=null
    private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
//            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        })

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }
}
