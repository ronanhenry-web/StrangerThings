package com.strangerthings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class CharactersViewModel: ViewModel() {

    // Live Data
    var list = MutableLiveData<ArrayList<CharactersBean>>()
    var errorMessage = MutableLiveData("")

    fun loadData() {
        errorMessage.postValue("")

        thread {
            // Gestion d'erreurs sur l'appel d'API
            try {
                //Appel d'API
                val newData = ArrayList(RequestUtils.loadStrangerThings().toList())
                list.postValue(newData)
            }
            catch(e:Exception){
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
        }
    }

}