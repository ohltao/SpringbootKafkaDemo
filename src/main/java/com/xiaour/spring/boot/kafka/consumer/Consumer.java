package com.xiaour.spring.boot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {

    @KafkaListener(topics = {"test1"}, containerFactory = "kafkaListenerContainerFactory1")
    public void listen1(ConsumerRecord<?, ?> record){
        System.out.println("-----------------------------");
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("---->"+message);
        }
    }

    @KafkaListener(topics = {"test2"}, containerFactory = "kafkaListenerContainerFactory2")
    public void listen2(ConsumerRecord<?, ?> record){
        System.out.println("============================");
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("---->"+message);
        }
    }
}
