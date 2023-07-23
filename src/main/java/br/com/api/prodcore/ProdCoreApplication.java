package br.com.api.prodcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ProdCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdCoreApplication.class, args);
	}
	
}
