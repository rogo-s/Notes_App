package com.rogo.ch4.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(private val context: Context) {
    val getFilter: Flow<String> = context.dataStore.data.map {
        it[FILTER_KEY] ?: ""
    }

    suspend fun setFilter(filter: String) {
        context.dataStore.edit {
            it[FILTER_KEY] = filter
        }
    }

    companion object {
        private const val DATASTORE_NAME = "datastore_preferences"
        private val FILTER_KEY = stringPreferencesKey("filter_key")
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}