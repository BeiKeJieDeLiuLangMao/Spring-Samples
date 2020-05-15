package bbm.service;

import bbm.model.MoneyMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author bbm
 * @date 2020/5/14
 */
@Service
@Slf4j
public class MessageQueueProducer {

    static final String TOPIC = "test-topic";
    @Autowired
    private RocketMQTemplate mqTemplate;

    @Scheduled(fixedDelay = 5 * 1000)
    public void produceMessage() {
        MoneyMessage message = new MoneyMessage(0, 1);
        mqTemplate.convertAndSend(TOPIC, message);
        log.info("Send a message: {}", message);
    }

    @Scheduled(fixedDelay = 5 * 1000)
    public void asyncProduceMessage() {
        MoneyMessage message = new MoneyMessage(1, 2);
        mqTemplate.asyncSend(TOPIC, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult result) {
                log.info("Async send message success, {}", result);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("Async send message exception", throwable);
            }
        });
        log.info("Async send a message: {}", message);
    }
}
