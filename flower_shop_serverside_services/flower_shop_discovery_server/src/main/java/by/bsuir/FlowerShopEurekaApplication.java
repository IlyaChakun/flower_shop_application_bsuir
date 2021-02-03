package by.bsuir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlowerShopEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerShopEurekaApplication.class, args);
	}

}
