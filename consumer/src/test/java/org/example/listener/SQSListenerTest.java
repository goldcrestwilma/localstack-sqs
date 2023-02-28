package org.example.listener;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

//@ExtendWith(LocalstackDockerExtension.class)
//@LocalstackDockerProperties(services = "sqs")
@SpringBootTest
class SQSListenerTest {

    @Autowired
    AmazonSQSAsync amazonSQS;

    @Autowired
    SQSListener sqsListener;

    @BeforeEach
    void setUp() {
        Map<String, Object> messageHeaders = Map.of("contentType", "application/json");
        String payload = "{ \"message\": \"hello\"";
        amazonSQS.createQueue("test-queue");
        amazonSQS.sendMessage("test-queue", "hello");
    }

    @Test
    void name() {
        // given

        // when

        // then
    }
}
