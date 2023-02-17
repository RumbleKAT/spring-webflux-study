package dev.rumblekat.webflux

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class MyRouter{

    @Bean
    fun helloRouter(handler: HelloHandler): RouterFunction<ServerResponse> =
        route()
            .GET("/",handler::sayHello)
            .build()

    //router 함수는 configration으로 등록하고, Bean으로 등록한다.
    @Bean
    fun userRouter(handler: UserHandler) : RouterFunction<ServerResponse> =
        router {
            "/users".nest{
                GET("/{id}",handler::getUser)
                GET("",handler::getAll)
            }
        }

}