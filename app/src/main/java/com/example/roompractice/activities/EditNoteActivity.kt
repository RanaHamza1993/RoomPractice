package com.example.roompractice.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.roompractice.R
import com.example.roompractice.db.entities.NoteEntity
import com.example.roompractice.viewmodels.EditNoteViewModel

class EditNoteActivity : AppCompatActivity() {
    private var etNote: EditText? = null
    private var bundle: Bundle? = null
    private var noteId: String? = null
    private var note: LiveData<NoteEntity>? = null

    internal var noteModel: EditNoteViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        etNote = findViewById(R.id.etNote)

        bundle = intent.extras

        if (bundle != null) {
            noteId = bundle!!.getString("note_id")
        }

        noteModel = ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
        note = noteModel?.getNote(noteId!!)
        note!!.observe(this, Observer { note -> etNote!!.setText(note!!.mNote) })
    }

    fun updateNote(view: View) {
        val updatedNote = etNote!!.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra(NOTE_ID, noteId)
        resultIntent.putExtra(UPDATED_NOTE, updatedNote)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun cancelUpdate(view: View) {
        finish()
    }

    companion object {

        val NOTE_ID = "note_id"
        internal val UPDATED_NOTE = "note_text"
    }
}
