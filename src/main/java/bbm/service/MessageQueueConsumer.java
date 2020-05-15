package bbm.service;

import bbm.model.MoneyMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import static bbm.service.MessageQueueProducer.TOPIC;

/**
 * @author bbm
 * @date 2020/5/14
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = TOPIC, consumerGroup = "cart-consumer_cart-item-add-topic")
public class MessageQueueConsumer implements RocketMQListener<MoneyMessage> {

    @Override
    public void onMessage(MoneyMessage message) {
        log.info("Received a message: {}", message);
    }
}
