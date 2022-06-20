package com.swm.baseframework.app.member.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserCreatedEventHandler {

    @Async
    @TransactionalEventListener(
            classes = UserCreatedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
//    @EventListener(UserCreatedEvent.class)
    public void handle(UserCreatedEvent event) {
        // TODO send SNS(email, sms, etc..)
        System.out.println("user id > " + event.getUserId());
    }
}
