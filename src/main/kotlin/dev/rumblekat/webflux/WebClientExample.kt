package dev.rumblekat.webflux

import dev.rumblekat.webflux.book.Book
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@RestController
class WebClientExample {

    val url = "http://localhost:8080/books"
    val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/books/block")
    fun getBooksBlockingWay(): List<Book>{
        log.info("Start RestTemplate")

        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Book>>(){}
            )
        // 복수개의 처리를 해야하면, 논블로킹 API를 써서 처리를 해야했는데, webClient에서 처리 가능
        // 스프링 5부터 도입된 것으로, 두방식다 사용가능함. 동시에 여러 서버로 호출이 가능.


        val result = response.body!!
        log.info("result : {}", result);
        log.info("Finish RestTemplate");
        // RestTemplate은 코드가 순차적으로 동작, 스레드가 블로킹되므로 다른 일을 할수 없다.
        return result
    }

    @GetMapping("/books/nonblock")
    fun getBooksNonBlockingWay(): Flux<Book> {
        log.info("Start Webclient")
        val flux = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Book::class.java)
            .map {
                log.info("result: {}",it)
                it
            }
        log.info("Finish")
        return flux
    }
    /*
    * 2023-02-19T12:27:11.259+09:00  INFO 4936 --- [ctor-http-nio-2] dev.rumblekat.webflux.WebClientExample   : Start Webclient
      2023-02-19T12:27:11.460+09:00  INFO 4936 --- [ctor-http-nio-2] dev.rumblekat.webflux.WebClientExample   : Finish -> 논 블로킹으로 사용된다.
      2023-02-19T12:27:11.902+09:00  INFO 4936 --- [ctor-http-nio-2] dev.rumblekat.webflux.WebClientExample   : result: Book(id=1, name=kotlin in action, price=3000)
      2023-02-19T12:27:11.913+09:00  INFO 4936 --- [ctor-http-nio-2] dev.rumblekat.webflux.WebClientExample   : result: Book(id=2, name=kotlin in action 2, price=4000)
    *
    * */

}