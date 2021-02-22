package by.bsuir.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//@SpringBootApplication(scanBasePackages = {"by.bsuir"})
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "by.bsuir.remoteclients")
//@RibbonClient(name = "auth-service", configuration = RibbonConfiguration.class)

@SpringBootApplication(scanBasePackages = {"by.bsuir"})
@EnableDiscoveryClient

@EnableFeignClients(basePackages = "by.bsuir.remoteclients")
@RibbonClient(name = "auth-service", configuration = RibbonConfiguration.class)
//@EnableCircuitBreaker
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FloristServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FloristServiceApplication.class, args);
    }
}
