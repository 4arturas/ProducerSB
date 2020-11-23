package com.isignthis.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@SpringBootApplication
public class ProducerApp
{

	@GetMapping("/")
	public String index(final Model model)
	{
		model.addAttribute("title", "Producer: Docker + Spring Boot + Kafka + Axios");
		return "index";
	}



	public static void main(String[] args)
	{
		SpringApplication.run(ProducerApp.class, args);
	}

}
