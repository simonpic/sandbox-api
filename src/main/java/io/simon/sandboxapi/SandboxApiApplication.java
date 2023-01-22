package io.simon.sandboxapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SandboxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxApiApplication.class, args);
	}

}
