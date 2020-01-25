package com.example.roompractice.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.activities.EditNoteActivity
import com.example.roompractice.activities.MainActivity
import com.example.roompractice.db.entities.NoteEntity

class NoteListAdapter(
    private val mContext: Context,
    private val onDeleteClickListener: OnDeleteClickListener?
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val layoutInflater: LayoutInflater
    private var mNotes: List<NoteEntity>? = null

    interface OnDeleteClickListener {
        fun OnDeleteClickListener(myNote: NoteEntity)
    }

    init {
        layoutInflater = LayoutInflater.from(mContext)
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: NoteViewHolder, position: Int) {

        if (mNotes != null) {
            val note = mNotes!![position]
            holder.setData(note.mNote, position)
            holder.setListeners()
        } else {
            // Covers the case of data not being ready yet.
            holder.noteItemView.setText(R.string.no_note)
        }
    }

    override fun getItemCount(): Int {
        return if (mNotes != null)
            mNotes!!.size
        else
            0
    }

    fun setNotes(notes: List<NoteEntity>) {
        mNotes = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteItemView: TextView
        private var mPosition: Int = 0
        private val imgDelete: ImageView
        private val imgEdit: ImageView

        init {
            noteItemView = itemView.findViewById(R.id.txvNote)
            imgDelete = itemView.findViewById(R.id.ivRowDelete)
            imgEdit = itemView.findViewById(R.id.ivRowEdit)
        }

        fun setData(note: String, position: Int) {
            noteItemView.text = note
            mPosition = position
        }

        fun setListeners() {
            imgEdit.setOnClickListener {
                val intent = Intent(mContext, EditNoteActivity::class.java)
                intent.putExtra("note_id", mNotes!![mPosition].id)
                (mContext as Activity).startActivityForResult(
                    intent,
                    MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE
                )
            }

            imgDelete.setOnClickListener {
                onDeleteClickListener?.OnDeleteClickListener(mNotes!![mPosition])
            }
        }
    }
}
