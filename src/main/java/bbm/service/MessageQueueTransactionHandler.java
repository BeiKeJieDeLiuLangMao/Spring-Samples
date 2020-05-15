package bbm.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author bbm
 * @date 2020/5/14
 */
@Service
@Slf4j
@RocketMQTransactionListener
public class MessageQueueTransactionHandler implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("Received a transaction, {}, {}", message, o);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("Received a transaction check, {}", message);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
