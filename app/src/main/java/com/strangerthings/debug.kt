package com.strangerthings

fun main() {
    val dataAll = RequestUtils.loadStrangerThings()
    //println(dataAll)
    println("Je suis : ${dataAll[0].name}")

    /*for(i in dataAll) {
        println(i.residence)
    }*/
}