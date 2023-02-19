package dev.rumblekat.webflux.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(){
    // run blocking or suspend 형식으로 바꿔줘야됨
    doSomething()
}

fun printHello() = println("Hello")

suspend fun doSomething() = coroutineScope{
    // 일시중단이 가능하고, 재기가 가능하다.
    // coroutineScope를 쓰면 블록킹되지않는다.
    launch {
        delay(200)
        println("world")
    }

    launch {
        printHello()
    }
}