package com.example.roompractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.roompractice.R

class NewNoteActivity : AppCompatActivity() {

    val NOTE_ADDED = "new_note"

    private var etNewNote: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        etNewNote = findViewById(R.id.etNewNote)

        val button = findViewById<Button>(R.id.bAdd)
        button.setOnClickListener(View.OnClickListener {
            val resultIntent = Intent()

            if (TextUtils.isEmpty(etNewNote?.getText())) {
                setResult(RESULT_CANCELED, resultIntent)
            } else {
                val note = etNewNote?.getText().toString()
                resultIntent.putExtra(NOTE_ADDED, note)
                setResult(RESULT_OK, resultIntent)
            }

            finish()
        })
    }

}
