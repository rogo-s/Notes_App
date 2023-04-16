package com.rogo.ch4.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDialogViewModel(private val localRepository: LocalRepository) : ViewModel() {

    fun addNotes(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.insertNote(noteEntity)
    }

}