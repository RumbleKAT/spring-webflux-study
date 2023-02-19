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
