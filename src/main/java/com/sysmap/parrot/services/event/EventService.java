package com.sysmap.parrot.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {
    @Autowired
    private KafkaTemplate<String, String> _kafka;
    @Value("{topic.name.producer}")
    private String topic;

    public void send(String event) {
        _kafka.send(topic, event);
    }
}
