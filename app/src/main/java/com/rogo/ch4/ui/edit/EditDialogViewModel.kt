package com.rogo.ch4.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.repository.LocalRepository
import kotlinx.coroutines.launch

class EditDialogViewModel(private val localRepository: LocalRepository) : ViewModel() {
    fun edit(noteEntity: NoteEntity) = viewModelScope.launch {
        localRepository.updateNote(noteEntity)
    }
}