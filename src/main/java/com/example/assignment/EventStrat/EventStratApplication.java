package com.example.assignment.EventStrat;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "CSV File Handler Api Documentation",
				description = "CSV File Handler Backend",
				version = "v1.0",
				contact = @Contact(
						name = "Ayush Aryan",
						email = "ayusharyan1309@gmail.com",
						url = "https://ayusharyan13.github.io/portfolio/"
				),
				license = @License(
						name = "Ayush Aryan",
						url = "https://www.linkedin.com/in/ayusharyan1309/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Github repo for reference",
				url = "https://github.com/ayusharyan13/CSV-File-Handler"
		)
)
public class EventStratApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventStratApplication.class, args);
	}

}
