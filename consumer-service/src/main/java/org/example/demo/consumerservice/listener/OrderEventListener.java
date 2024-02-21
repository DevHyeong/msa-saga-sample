package org.example.demo.consumerservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.consumerservice.messaging.ConsumerEventProducer;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final ConsumerEventProducer consumerEventProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderEventCommit(OrderEvent orderEvent){
        log.debug("order complete {}", orderEvent.toString());
        //consumerEventProducer.order("", );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleOrderEventRollback(OrderEvent orderEvent){
        log.debug("order cancel {}", orderEvent.toString());
        //consumerEventProducer.reviseOrder("", orderEvent.getOrderId());
    }
}
