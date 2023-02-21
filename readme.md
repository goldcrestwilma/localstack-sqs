# docker compose
```shell
./gradlew composeup
```

> LocalStack Configuration
> 
> https://docs.localstack.cloud/references/configuration/

# 도커 접속
```shell
docker exec -it localstack sh
```

> AWS CLI
> 
> https://docs.localstack.cloud/user-guide/integrations/aws-cli/
 
# 큐 생성
```shell
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sample-queue.fifo --attributes FifoQueue=true
```
- FIFO 큐는 queue name suffix 로 `.fifo`를 붙인다.

# 큐 메시지 받기
```shell
aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/sample-queue.fifo --region us-east-1 --output json | cat
```

# 실행순서
1. Docker 실행
2. docker-compose 실행
3. docker exec 접속 후 큐 생성
4. producer 실행
5. consumer 실행
6. 메시지 발행 
- POST http://localhost:8080/sendMessage

