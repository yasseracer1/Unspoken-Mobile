package org.d3if3130.unspoken.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3130.unspoken.database.CeritaDao
import org.d3if3130.unspoken.model.Cerita

class MainViewModel(dao: CeritaDao) : ViewModel() {

    val data: StateFlow<List<Cerita>> = dao.getCerita().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}