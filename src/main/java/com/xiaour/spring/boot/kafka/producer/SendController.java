package com.xiaour.spring.boot.kafka.producer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
public class SendController {

    @Resource
    private Producer producer;

    @RequestMapping(value = "/sendTest1")
    public Boolean send1() {
        producer.send1();
        return Boolean.TRUE;
    }
    @RequestMapping(value = "/sendTest2")
    public Boolean send2() {
        producer.send2();
        return Boolean.TRUE;
    }
}
