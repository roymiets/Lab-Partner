package com.cm.tdbConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class TdbWebhookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdbWebhookApplication.class, args);
	}

}
