package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Reposistory.NoteReposistory

class NoteViewModelProviderFactory(
    val app: Application,
    private val noteReposistory: NoteReposistory
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteReposistory) as T
    }

}