package br.com.bolao.bolaoPaulista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BolaoPaulistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BolaoPaulistaApplication.class, args);
		
	}

}
