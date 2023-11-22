package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "DB assignment 2. Bonus Part",
                version = "1.0.0",
                description = "wish me luck",
                termsOfService = "nurzhandbassignment2",
                contact = @Contact(
                        name = "Nurzhan",
                        email = "nurzhan.kozhamuratov@nu.edu.kz"
                ),
                license = @License(
                        name = "nurzhan_licence",
                        url = "nurzhan.kozhamuratov@nu.edu.kz"
                )
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
