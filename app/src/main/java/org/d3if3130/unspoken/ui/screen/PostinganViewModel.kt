package org.d3if3130.unspoken.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3130.unspoken.model.Cerita

class PostinganViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Cerita> {
        val data = mutableListOf<Cerita>()
        for (i in 29 downTo 20) {
            data.add(
                Cerita(
                    id = 1,
                    "",
                    "Apalah ni matkul, bingung kali caranya mengoding begitu syulit.\n\n" +
                            "Tolong ajari saya cara mengoding",
                    "",
                    "3 hari"
                )
            )
        }
        return data
    }
}