package dev.rumblekat.webflux.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(){

    runBlocking {
        println("Hello")
        //-Dkotlinx.coroutines.debug argument에 추가 필요
        println(Thread.currentThread().name)
    }

    println("World")
    println(Thread.currentThread().name)
}