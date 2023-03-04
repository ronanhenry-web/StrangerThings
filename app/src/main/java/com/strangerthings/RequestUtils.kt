package com.strangerthings

import com.google.gson.Gson
import okhttp3.*
import java.io.InputStreamReader

// Tester gestion d'erreur modifier rajouter une lettre dans l'URL d'API
//const val URL_API = "https://stranger-things-api.fly.dev/api/v1/characters/s"
const val URL_API = "https://stranger-things-api.fly.dev/api/v1/characters/"


object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    /*fun loadStrangerThings(): CharactersBean {
        val json = sendGet(URL_API)

        return gson.fromJson(json, CharactersBean::class.java)
    }*/

    /*fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()

        return client.newCall(request).execute().use {

            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }*/

    // Avec un Array
    fun loadStrangerThings() = sendGet(URL_API).use {
        var inputSR = InputStreamReader(it.body.byteStream())
        gson.fromJson(inputSR, Array<CharactersBean>::class.java)
    }

    // Avec un ArrayList
    fun loadArrayList() = sendGet(URL_API).use {
        var inputSR = InputStreamReader(it.body.byteStream())
        gson.fromJson(inputSR, ArrayList<CharactersBean>()::class.java)
    }

    // Appel d'API web
    fun sendGet(url: String): Response {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw Exception("Réponse du serveur incorrect : ${response.code}, voir API Url")
        }
        return response
    }
}