package by.bsuir.flowershop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"by.bsuir.flowershop"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}