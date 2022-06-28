package com.auk.domain.events.publisher;

import com.auk.domain.events.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent> {
    void publish(T domainEvent);
}
