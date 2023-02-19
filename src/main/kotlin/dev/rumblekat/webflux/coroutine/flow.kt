package dev.rumblekat.webflux.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val flow = simple()
    flow.collect{ value -> println(value) } // flow도 subscribe처럼 terminal operator
}

fun simple(): Flow<Int> = flow{
    println("flow started")
    for(i in 1..3){
        delay(100)
        emit(i)
    }
}