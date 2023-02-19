# 정리

## R2DBC
- 빠르게 성장 중인 리액티브 기반의 비동기-논블로킹 데이터베이스 드라이버
- 다양한 데이터베이스를 지원한다. 
- 리액티브 스트림 구현체인 Project Reactor, RxJava 등을 지원한다.
- 마지막엔 subscribe를 해줘야 동작함.

### 스프링 데이터 R2DBC
- 스프링 webflux와 같이 사용하면, 전구간 비동기-논블로킹 앱가능
- 많은 ORM에서 지원하는 LazyLoading, Dirty-checking, Cache 등을 지원하진 않음.

### ReactiveCrudRepository
- 기존의 CrudRepository의 R2DBC버전, 모든 반환타입이 Mono나 Flux와 같은 publisher 타입으로 리턴된다.

## Spring WebFlux의 코루틴 지원
코루틴 이전에선, 리엑티브를 썼는데, 함수형 프로그래밍이 적응을 해야하는 러닝커브가 늘어남
요즘엔, 코루틴 기반의 소스가 많아지고 있음.

코루틴은 멀티 플랫폼을 지원하고, **일시 중단 가능한 함수**를 통해 스레드가 실행을 잠시 중단했다가
중단한 시점부터 다시 **재개** 할 수 있다.

Spring Webflux도 Coroutine을 공식적으로 지원
Coroutine에선 Mono = suspend / Flux = flow 로 변환된다. 
구조적 동시성 학습이 쉬워지고, 디버깅이 쉬워진다.

**awaitXXX** 시작하는 확장 함수를 사용하면, 즉시 코루틴으로 변환 가능
(ex awaitSingle) 
CoroutineCrudRepository를 사용하면, Await을 사용할 필요없이 사용 가능함.


