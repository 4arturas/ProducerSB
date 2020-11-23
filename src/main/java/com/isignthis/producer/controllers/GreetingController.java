package com.isignthis.producer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GreetingController
{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value( value = "${kafka.topic.example-topic}")
    private String topicExampleTopic;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model)
    {
        model.addAttribute("name", name);
        sendMessage( "11" );
        return "greeting";
    }

    public void sendMessageSimple(String msg)
    {
        final String topicName = topicExampleTopic;
        kafkaTemplate.send(topicName, msg);
    }

    public void sendMessage( final String message )
    {

        final String topicName = topicExampleTopic;
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
        {

            @Override
            public void onSuccess(SendResult<String, String> result)
            {
                final String info = String.format("Sent message=[%s] with offset=[%s]", message, result.getRecordMetadata().offset() );
                log.info( info );
            }
            @Override
            public void onFailure(Throwable ex)
            {
                final String info = String.format( "Unable to send message=[%s] due to : %s", message, ex.getMessage() );
                log.info( info );
            }
        });
    }
}
