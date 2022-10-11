package com.xiaour.spring.boot.kafka.consumer.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> brokers;

    private final String group1 = "test1";
    private final String group2 = "test2";

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory1() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory1());
        factory.setConcurrency(4);
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2());
        factory.setConcurrency(4);
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    public Map<String, Object> getCommonProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group1);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return properties;
    }

    public ConsumerFactory<String, String> consumerFactory1() {
        Map<String, Object> properties = getCommonProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group1);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    public ConsumerFactory<String, String> consumerFactory2() {
        Map<String, Object> properties = getCommonProperties();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group2);
        return new DefaultKafkaConsumerFactory<>(properties);
    }
}