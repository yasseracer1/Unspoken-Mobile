package org.d3if3130.unspoken.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3130.unspoken.database.CeritaDao
import org.d3if3130.unspoken.ui.screen.DetailViewModel
import org.d3if3130.unspoken.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: CeritaDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}