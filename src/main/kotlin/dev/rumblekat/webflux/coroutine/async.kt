package dev.rumblekat.webflux.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun sum(a:Int, b:Int) = a + b
fun main() = runBlocking<Unit>{
    val result1 = async {
        delay(100)
        sum(1,2)
    } //Deferred 타입으로 리턴되고, async 형식으로 리턴된다. 키워드가 아닌 함수로 지정

    println("result1 : ${result1.await()}")

    val result2 = async{
        delay(200)
        sum(2,5)
    }

    println("result2 : ${result2.await()}")

}