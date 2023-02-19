package dev.rumblekat.webflux.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
//    launch {
//        // 스레드 차단없이 씀
//        delay(500) // Thread.sleep 과 다르다. 얘는 멈춤
//        println("World")
//    }
//    println("Hello")

    val job1 : Job = launch {
        val elapasedTime = measureTimeMillis {
            delay(140)
        }
        println("async test-1 $elapasedTime ms")
    }
    job1.cancel()

    val job2 : Job = launch(start = CoroutineStart.LAZY) {
        val elapasedTime = measureTimeMillis {
            delay(100)
        }
        println("async test-2 $elapasedTime ms")
    }

    job2.start()
}