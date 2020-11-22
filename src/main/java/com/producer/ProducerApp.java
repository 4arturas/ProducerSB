package com.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void sendMessageSimple(String msg)
	{
		final String topicName = "example-topic";
		kafkaTemplate.send(topicName, msg);
	}

	public void sendMessage(String message) {

		final String topicName = "example-topic";
		ListenableFuture<SendResult<String, String>> future =
				kafkaTemplate.send(topicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Sent message=[" + message +
						"] with offset=[" + result.getRecordMetadata().offset() + "]");
			}
			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message=["
						+ message + "] due to : " + ex.getMessage());
			}
		});
	}

	public static void main(String[] args)
	{
		SpringApplication.run(ProducerApp.class, args);
	}

}
