package ua.bibusukraine.mediainsightsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MediaInsightsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaInsightsServiceApplication.class, args);
	}

}
