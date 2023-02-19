package dev.rumblekat.webflux.book

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookRepository : ReactiveCrudRepository<Book,Long> {
    fun findByName(name: String) : Mono<Book>
    //항상 타입은 Mono나 flux
}