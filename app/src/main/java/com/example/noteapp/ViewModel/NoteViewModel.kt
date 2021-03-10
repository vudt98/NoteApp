package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Model.Note
import com.example.noteapp.Reposistory.NoteReposistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application,
    private val noteReposistory : NoteReposistory
): AndroidViewModel(app) {

    fun addNote(note : Note) = viewModelScope.launch {
        noteReposistory.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteReposistory.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteReposistory.deleteNote(note)
    }

    fun getAllNote() = noteReposistory.getAllNotes()


}