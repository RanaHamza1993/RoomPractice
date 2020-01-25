package com.example.roompractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = this.javaClass.simpleName

    override fun onCleared() {
        super.onCleared()
    }
}
