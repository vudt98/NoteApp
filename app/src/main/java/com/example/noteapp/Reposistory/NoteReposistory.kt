package com.example.noteapp.Reposistory

import com.example.noteapp.Db.NoteDatabase
import com.example.noteapp.Model.Note

class NoteReposistory(private val db: NoteDatabase) {

    suspend fun addNote(note : Note) = db.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
}