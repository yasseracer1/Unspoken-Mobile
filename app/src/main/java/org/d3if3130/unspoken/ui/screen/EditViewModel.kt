package org.d3if3130.unspoken.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3130.unspoken.database.CeritaDao
import org.d3if3130.unspoken.model.Cerita
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: CeritaDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US)
    fun insert(judul: String, catatan: String, tema: String) {
        val cerita = Cerita(
            judul = judul,
            catatan = catatan,
            tema = tema,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(cerita)
        }
    }
    suspend fun getMahasiswa(id: Long): Cerita? {
        return dao.getCeritaById(id)
    }

    fun update(id: Long, judul: String, catatan: String, tema: String) {
        val cerita = Cerita(
            id = id,
            judul = judul,
            catatan = catatan,
            tema = tema,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(cerita)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}