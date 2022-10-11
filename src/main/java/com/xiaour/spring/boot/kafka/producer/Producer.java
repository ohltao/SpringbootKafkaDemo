package com.xiaour.spring.boot.kafka.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Component
public class Producer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send1() {
        Message message = new Message();
        message.setId("KFK_"+System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("test1", gson.toJson(message));
    }
    //发送消息方法
    public void send2() {
        Message message = new Message();
        message.setId("KFK_"+System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("test2", gson.toJson(message));
    }
}
