package com.apurva.kafkaconsumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apurva.kafkaconsumer.config.*;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {
	
	List<String> messages=new ArrayList<>();
	
	User userFromTopic = null;
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg(){
		return messages;
	}

	@KafkaListener(groupId="appu-1", topics="daimler", containerFactory="kafkaListenerContainerFactory")
	public List<String> getMessageFromTopic(String data) {
		messages.add(data);
		return messages;
		
	}
	
	//For Json
	
	@GetMapping("/consumeJson")
	public User consumeJson() {
		return userFromTopic;
	}
	
	@KafkaListener(groupId="appu-2", topics="daimler", containerFactory="userKafkaListenerContainerFactory")
	public User getJsonFromTopic(User user) {
		userFromTopic = user;
		return userFromTopic;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
