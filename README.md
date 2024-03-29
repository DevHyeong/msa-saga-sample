## [도서] 마이크로서비스 아키텍처를 참고하여 구현한 saga 샘플 코드

### 서비스 종류
- 주문(order), 소비자(consumer), 주방(restaurant), 회계(account)

### 주문 생성 시가의 로컬 트랜잭션
- 주문 서비스: 주문을 APPROVAL_PENDING 상태로 생성
- 소비자 서비스: 주문 가능한 소비자인지 확인
- 주방 서비스: 주문 내역을 확인하고 티켓을 CREATE_PENDING 상태로 생성
- 회계 서비스: 소비자 신용카드를 승인
- 주방 서비스: 티켓 상태를 AWAITING_ACCEPTANCE로 변경
- 주문 서비스: 주문 상태를 APPROVED로 변경


### 시가 오케스트레이터 로직
1. 사가 오케스트레이터가 소비자 확인 커맨드를 소비자 서비스에 전송한다.
2. 소비자 서비스는 소비자 확인 메시지를 응답한다.
3. 사가 오케스트레이터는 티켓 생성 커맨드를 주방 서비스에 전송한다.
4. 주방 서비스는 티켓 생성 메시지를 응답한다.
5. 사가 오케스트레이터는 신용카드 승인 메시지를 회계 서비스에 전송한다.
6. 회계 서비스는 신용카드 승인됨 메시지를 응답한다.
7. 사가 오케스트레이터는 티켓 승인 커맨드를 주방 서비스에 전송한다.
8. 사가 오케스트레이터는 주문 승인 커맨드를 주문 서비스에 전송한다.

### 스펙
- 카프카
- JPA

### 기타
- 저자는 저자가 개발한 이벤추에이트 트램을 사용했지만 특정 프레임워크에 의존하지 않고 간단한 샘플코드를 작성하기 위함이니 이벤추에이트 트램을 제외하여 개발


### 도커 카프카 실행 명령어
```shell
docker-compose -f docker-compose.yml up -d # docker-compose 실행
docker-compose exec [카프카서버이름] kafka-topics --create --topic [토픽이름] --bootstrap-server [카프가서버이름:포트] --replication-factor 3 --partitions 2 # 토픽생성

docker-compose exec [카프카서버이름] bash # 컨테이너 내부 접속
kafka-console-consumer --topic [토픽이름] --bootstrap-server [카프카서버이름:포트] # 컨슈머 실행

docker-compose down #docker-compose 컨테이너 내리기
```


### 레퍼런스
- https://github.com/gilbutITbook/007035/tree/master
- https://devocean.sk.com/blog/techBoardDetail.do?ID=164016
- https://github.com/confluentinc/cp-all-in-one/blob/7.5.3-post/cp-all-in-one/docker-compose.yml
- https://techblog.woowahan.com/7835/
