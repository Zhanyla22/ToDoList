package com.example.todolist.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "ToDo List API documentation",
        description = """
                               
                """,
        contact = @Contact(
                name = "Zhanylai Mamytova",
                email = "ja.mamytova@gmail.com"
        ),
        version = "v1"))
public class OpenAPIConfig {
}
