package dev.rumblekat.webflux.book

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/repo")
class BookRepoController(
    val bookRepository: BookRepository,
) {
    val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/mono")
    fun returnHello(): Mono<String>{
        val m: Mono<String> = "Hello Mono".toMono()
        logger.info("response: {}",m.toString())
        return m
    }

    @GetMapping("{name}")
    fun getByName(@PathVariable name:String) : Mono<Book> {
        return bookRepository.findByName(name)
    }

    @PostMapping
    fun create(@RequestBody map:Map<String,Any>) :Mono<Book>{
        val book = Book(
            name = map["name"].toString(),
            price = map["price"] as Int
        )
        return bookRepository.save(book)
    }
}