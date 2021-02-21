package by.bsuir.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"by.bsuir"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "by.bsuir.remoteclients")
@RibbonClient(name = "account-service", configuration = RibbonConfiguration.class)
public class FloristServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FloristServiceApplication.class, args);
    }
}
