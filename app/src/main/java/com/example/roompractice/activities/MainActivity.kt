package com.example.roompractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.roompractice.R
import com.example.roompractice.extensions.showInfoMessage
import com.example.roompractice.extensions.showSuccessMessage
import com.example.roompractice.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private var noteViewModel: NoteViewModel?=null
    private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        })

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //code to insert the note
            showSuccessMessage("Note inserted")

        }else{
            showInfoMessage("Note not inserted")
        }
    }
}
