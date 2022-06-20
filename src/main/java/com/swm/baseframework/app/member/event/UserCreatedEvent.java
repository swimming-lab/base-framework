package com.swm.baseframework.app.member.event;

import com.swm.baseframework.base.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreatedEvent extends Event {
    private Long userId;
}
