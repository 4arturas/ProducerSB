package com.producer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProduceController
{
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value( value = "${kafka.topic.example-topic}" )
    private String topicExample;

    void sendMesage( final String msg )
    {
        kafkaTemplate.send( topicExample, msg );
    }

    @GetMapping("/produce/{msg}")
    public String produce( @PathVariable String msg )
    {
        sendMesage( msg );
        return "received " + msg;
    }
}
