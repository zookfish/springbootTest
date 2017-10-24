package com.example.demo.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfiguration {
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("zookfish.queue");
	}
	
	
}
