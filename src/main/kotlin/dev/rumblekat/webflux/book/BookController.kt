package dev.rumblekat.webflux.book

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(
    private val bookService: BookService
) {
    @GetMapping("/books")
    fun getAll() : Flux<BookDto> {
        // 스프링에서 자동으로 subscribe를 해줘서 별도의 선언이 필요없음
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id:Int) : Mono<BookDto> {
        return bookService.get(id)
    }

    @PostMapping("/books")
    fun add(@RequestBody request: Map<String, Any>): Mono<BookDto>{
        return bookService.add(request)
    }

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id:Int):Mono<Void>{
        return bookService.delete(id)
    }

}