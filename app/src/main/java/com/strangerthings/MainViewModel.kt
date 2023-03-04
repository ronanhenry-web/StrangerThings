package com.strangerthings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.thread
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Live Data
    private val _data = MutableLiveData<Array<CharactersBean>>()
    val data: LiveData<Array<CharactersBean>> = _data
    var errorMessage = MutableLiveData("")

    fun loadData() {
        errorMessage.postValue("")

        thread {
            // Gestion d'erreurs sur l'appel d'API
            try {
                //Appel d'API
                val newData = RequestUtils.loadStrangerThings()
                _data.postValue(newData)
            }
            catch(e:Exception){
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
        }
    }
}