package bbm.service;

import bbm.model.MoneyMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author bbm
 * @date 2020/5/14
 */
@Service
@Slf4j
public class MessageQueueTransactionCreator {

    static final String TOPIC = "test-transaction";
    @Autowired
    private RocketMQTemplate mqTemplate;

    @Scheduled(fixedDelay = 5 * 1000)
    public void produceMessage() {
        MoneyMessage message = new MoneyMessage(0, 1);
        ;
        mqTemplate.sendMessageInTransaction(TOPIC, MessageBuilder.withPayload(message).build(), null);
        log.info("Send a message: {}", message);
    }
}
