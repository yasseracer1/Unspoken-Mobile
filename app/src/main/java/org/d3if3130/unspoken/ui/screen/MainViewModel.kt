package org.d3if3130.unspoken.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3130.unspoken.model.PostPostingan
import org.d3if3130.unspoken.model.Postingan
import org.d3if3130.unspoken.model.RequestIdPostingan
import org.d3if3130.unspoken.model.RequestUsername
import org.d3if3130.unspoken.network.PostinganApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Postingan>())
        private set
    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun clearMessage() { errorMessage.value = null }

    fun getPostingan(id: String): RequestIdPostingan {
        return RequestIdPostingan(id)
        Log.d("COBA", "$id")
    }
    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = PostinganApi.service.getPostingan()
            } catch (e: Exception) {
                Log.d("MainViewModel1", "Failure: ${e.message}")
            }
        }
    }

    fun retrievePrivateUserPostingan(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = PostinganApi.service.getUserPostingan(RequestUsername(username))
            } catch (e: Exception) {
                Log.d("MainViewModel2", "Failure: ${e.message}")
            }
        }
    }

    fun retrieveDetailPostingan(id_postingan: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = PostinganApi.service.getPostinganDetail(RequestIdPostingan(id_postingan))
            } catch (e: Exception) {
                Log.d("MainViewModel3", "Failure: ${e.message}")
            }
        }
    }

    fun saveData(username: String, postingan: String, suka: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val post = PostPostingan(
                    username = username,
                    postingan = postingan,
                    suka = suka
                )

                val result = PostinganApi.service.postPostingan(
                    post
                )

                if (result.status == "success")
                    retrieveData()
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}