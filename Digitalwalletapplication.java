package com.wallet;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
 
@SpringBootApplication
@EnableJpaAuditing
public class DigitalWalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalWalletApplication.class, args);
        System.out.println("===== Digital Wallet System Started Successfully =====");
        System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
    }
}
 