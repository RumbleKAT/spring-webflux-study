package dev.rumblekat.webflux

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class User(val id:Long, val email:String)

@Component
class UserHandler {

    // router 함수로부터 ServerRequest를 받고, ServerResponse를 리턴한다. 기본적으론 불변객체이다.
    val users = listOf(
        User(1,"user1@gmail.com"),
        User(2,"user2@gmail.com")
    )

    fun getUser(req: ServerRequest): Mono<ServerResponse> =
        users.find { req.pathVariable("id").toLong() == it.id }
            ?.let {
                ServerResponse.ok().bodyValue(it)
            } ?: ServerResponse.notFound().build()

    fun getAll(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().bodyValue(users)

}
