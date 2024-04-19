package br.com.telefonica.ms.linedataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LineDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LineDataServiceApplication.class, args);
	}

}
