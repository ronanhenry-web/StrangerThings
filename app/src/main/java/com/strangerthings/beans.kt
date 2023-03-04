package com.strangerthings

// Class model JSON API
data class CharactersBean(
    val id: String? = "",
    val aliases: List<String>? = null,
    val otherRelations: List<String>? = null,
    val affiliation: List<String>? = null,
    val occupation: List<String>? = null,
    val residence: List<String>? = null,
    val appearsInEpisodes: List<String>? = null,
    val photo: String? = "",
    val name: String? = "",
    val status: String? = "",
    val born: String? = "",
    val gender: String? = "",
    val eyeColor: String? = "",
    val hairColor: String? = "",
    val portrayedBy: String? = "",
)