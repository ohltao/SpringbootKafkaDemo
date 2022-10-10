package com.xiaour.spring.boot.kafka.producer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
public class SendController {

    @Resource
    private Producer producer;

    @RequestMapping(value = "/send")
    public Boolean send() {
        producer.send();
        return Boolean.TRUE;
    }
}
