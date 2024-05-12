package org.d3if3130.unspoken.screen

import androidx.lifecycle.ViewModel
import org.d3if3130.unspoken.model.User

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<User> {
        val data = mutableListOf<User>()
        data.add(
            User(
                1,
                "Yasser Abdulah Ramadhan",
                "aefsef",
                "faeofj"
            )
        )
        data.add(
            User(
                1,
                "Yasser Abdulah Ramadhan",
                "aefsef",
                "faeofj"
            )
        )
        return data
    }
}