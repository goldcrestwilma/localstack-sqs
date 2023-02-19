package org.example.publish;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSEventPublisher {

    private final AmazonSQS amazonSQS;

    private final ObjectMapper objectMapper;

    public void publishEvent(JsonNode message) {
        log.info("Generating event : {}", message);
        String queueUri = "http://localhost:4566/000000000000/sample-queue.fifo";
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUri)
                .withMessageBody(objectMapper.writeValueAsString(message))
                .withMessageGroupId("SampleMessage")
                .withMessageDeduplicationId(UUID.randomUUID().toString());
            amazonSQS.sendMessage(sendMessageRequest);
            log.info("Event has been published in SQS.");
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Exception occurred while pushing event to sqs : {} and stacktrace ; {}", e.getMessage(), e);
        }
    }
}
