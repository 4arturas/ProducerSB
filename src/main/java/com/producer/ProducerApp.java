package com.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
@Slf4j
public class ProducerApp {

	@GetMapping("/")
	public String index(final Model model)
	{
		model.addAttribute("title", "Producer: Docker + Spring Boot");
		model.addAttribute("msg", "Welcome to the docker container!");
		sendMessage( "10" );
		return "index";
	}

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value( value = "${kafka.topic.example-topic}")
	private String topicExampleTopic;

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

	public static void main(String[] args)
	{
		SpringApplication.run(ProducerApp.class, args);
	}

}
