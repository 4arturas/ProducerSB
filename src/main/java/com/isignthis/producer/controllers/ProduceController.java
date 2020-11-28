package com.isignthis.producer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
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

    void sendMesageSimpleIplementation( final String msg )
    {
        log.info(msg);
        kafkaTemplate.send( topicExample, msg );
    }

    void sendMessage( final String message )
    {

        final String topicName = topicExample;
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
        {

            @Override
            public void onSuccess( SendResult<String, String> result )
            {
                final String info = String.format("Sent message=[%s] with offset=[%s]", message, result.getRecordMetadata().offset() );
                log.info( info );
            }
            @Override
            public void onFailure( Throwable ex )
            {
                final String info = String.format( "Unable to send message=[%s] due to : %s", message, ex.getMessage() );
                log.info( info );
            }
        });
    }

    @GetMapping("/produce/{msg}")
    public String produce( @PathVariable String msg )
    {
        sendMesageSimpleIplementation( msg );
        return msg;
    }
}
