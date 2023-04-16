package com.rogo.ch4.ui.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.repository.LocalRepository
import kotlinx.coroutines.launch

class DeleteDialogViewModel(private val localRepository: LocalRepository) : ViewModel() {
    fun delete(noteEntity: NoteEntity) =
        viewModelScope.launch { localRepository.deleteNote(noteEntity) }
}