package dev.rumblekat.webflux.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicInteger

data class BookDto(val id: Int, val name:String, val price:Int)

@Service
class BookService {

    private final val nextId = AtomicInteger(0)
    val books = mutableListOf(
        BookDto(id = nextId.incrementAndGet(), name="kotlin in action", price = 3000),
        BookDto(id = nextId.incrementAndGet(), name="kotlin in action 2", price = 4000)
    )

    fun getAll() : Flux<BookDto> { // 복수개의 경우 flux로 리턴한다.
//        return Flux.fromIterable(books) 같은 방식으로 작성을 할수있다.(Kotlin도 있음) Mono도 동일하다.
        return books.toFlux()
    }

    fun get(id: Int): Mono<BookDto> {
//        return Mono.justOrEmpty(books.find{ it.id == id })
        return books.find{ it.id == id }.toMono()
    }

    fun add(request: Map<String, Any>): Mono<BookDto> {
        return Mono.just(request)
            .map { map ->
                val book = BookDto(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
                .map { books.remove(it) }
                .then()
    }
}