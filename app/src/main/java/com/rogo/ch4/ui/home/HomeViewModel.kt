package com.rogo.ch4.ui.home

import androidx.lifecycle.*
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.repository.LocalRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> get() = _notes

    fun getNotesAsc() = viewModelScope.launch {
        _notes.postValue(localRepository.getNotesAsc())
    }

    fun getNotesDesc() = viewModelScope.launch {
        _notes.postValue(localRepository.getNotesDesc())
    }

    fun setFilter(filter: String) = viewModelScope.launch {
        localRepository.setFilter(filter)
    }

    fun getFilter() = localRepository.getFilter().asLiveData()
}