package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SQSListener {

    @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receiveMessage(@Payload String payload, @Headers Map<String, String> headers, Acknowledgment acknowledgment) {
        log.info("payload: {}", payload);
        log.info("headers {}", headers);
        /*
        header 값
        {
              SentTimestamp=1676811477504,
              ReceiptHandle=ZWQxMjVlOTctMThjNy00ZGZmLWI3YmQtMTFmMzBhMzE2YjdmIGFybjphd3M6c3FzOnVzLWVhc3QtMTowMDAwMDAwMDAwMDA6c2FtcGxlLXF1ZXVlLmZpZm8gMTU2NDQ3MDgtZmIwNy00MzkwLTkyYmUtMGY4Y2ZjYmE2NjMyIDE2NzY4MTE0NzcuNTA2NTA0NQ==,
              MessageGroupId=SampleMessage,
              SenderId=000000000000,
              LogicalResourceId=sample-queue.fifo,
              ApproximateReceiveCount=1,
              Acknowledgment=org.springframework.cloud.aws.messaging.listener.QueueMessageAcknowledgment@4f214498,
              SequenceNumber=14403697045074345989,
              Visibility=org.springframework.cloud.aws.messaging.listener.QueueMessageVisibility@3b427716,
              MessageDeduplicationId=79a68758-e316-4783-af58-900e0ab29d36,
              lookupDestination=sample-queue.fifo,
              ApproximateFirstReceiveTimestamp=1676811477506,
              MessageId=15644708-fb07-4390-92be-0f8cfcba6632
        }
         */
        acknowledgment.acknowledge();
    }
}
